package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Home;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.homeshare.homeshareapi.repository.HomeRepository;

import java.util.ArrayList;
import java.util.List;
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

        for(Home home : homes){
            if(home.getTitle().equalsIgnoreCase(criteria)
                    || String.valueOf(home.getEndRent()).equalsIgnoreCase(criteria)
                    || String.valueOf(home.getStartRent()).equalsIgnoreCase(criteria)
                    || home.getTiming().equalsIgnoreCase(criteria)){
                matchingHomes.add(home);
            }
        }
        return matchingHomes;
    }
}
