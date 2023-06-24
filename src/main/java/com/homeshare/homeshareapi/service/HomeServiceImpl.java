package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Home;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.homeshare.homeshareapi.repository.HomeRepository;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService{

    private final HomeRepository homeRepository;
    private List<Home> homes;

    @Override
    public Home create(Home home, MultipartFile image1, MultipartFile image2, MultipartFile image3) {
        // Save home details to the database
        Home createdHome = homeRepository.save(home);

        try {
            // Upload images to a directory on the server
            String imageDirectory = "src/main/resources/offerImages";
            String image1FileName = "image1_" + createdHome.getId().toString() + ".jpg";
            String image2FileName = "image2_" + createdHome.getId().toString() + ".jpg";
            String image3FileName = "image3_" + createdHome.getId().toString() + ".jpg";

            Path image1Path = Path.of(imageDirectory + image1FileName);
            Path image2Path = Path.of(imageDirectory + image2FileName);
            Path image3Path = Path.of(imageDirectory + image3FileName);

            Files.copy(image1.getInputStream(), image1Path, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(image2.getInputStream(), image2Path, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(image3.getInputStream(), image3Path, StandardCopyOption.REPLACE_EXISTING);

            // Update the home object with the image paths
            createdHome.setImage1(image1Path.toString());
            createdHome.setImage2(image2Path.toString());
            createdHome.setImage3(image3Path.toString());

            // Save the updated home object
            homeRepository.save(createdHome);
        } catch (Exception e) {
            // Handle exception
        }

        return createdHome;
    }

    @Override
    public List<Home> read() {
        return homeRepository.findAll();
    }

    @Override
    public Home modify(UUID id, Home homes) {
        return homeRepository.findById(id)
                .map(p -> {
                    p.setTitle(homes.getTitle());
                    p.setDescription(homes.getDescription());
                    p.setShared(homes.isShared());
                    p.setImage1(homes.getImage1());
                    p.setImage2(homes.getImage2());
                    p.setImage3(homes.getImage3());
                    p.setStartRent(homes.getStartRent());
                    p.setEndRent(homes.getEndRent());
                    p.setTiming(homes.getTiming());
                    return homeRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Home not Existed !"));
    }

    @Override
    public String delete(UUID id) {
        homeRepository.deleteById(id);
        return "Home Delete !";
    }

    @Override
    public List<Home> searchByCriteria(String criteria) {
        List<Home> matchingHomes = new ArrayList<>();
        List<Home> allHomes = homeRepository.findAll();

        for(Home home : allHomes){
            if(matchesCriteria(home,criteria)) {
                matchingHomes.add(home);
            }
        }
        return matchingHomes;
    }

    private boolean matchesCriteria(Home home, String criteria) {
        String title = home.getTitle();
        String endRent = String.valueOf(home.getEndRent());
        String startRent = String.valueOf(home.getStartRent());
        String timing = home.getTiming();
        String description = home.getDescription();

        return ((title != null && title.toLowerCase().contains(criteria.toLowerCase()))
                || (endRent != null && endRent.contains(criteria))
                || (startRent != null && startRent.contains(criteria))
                || (timing != null && timing.toLowerCase().contains(criteria.toLowerCase()))
                || (description != null && description.toLowerCase().contains(criteria.toLowerCase()))
                && home.isShared());
    }
}
