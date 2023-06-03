package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Home;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.homeshare.homeshareapi.repository.HomeRepository;

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
    public Home create(Home home) {
        return homeRepository.save(home);
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
