package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Home;
import com.homeshare.homeshareapi.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface HomeService {

    Home create(Home home, MultipartFile image1, MultipartFile image2, MultipartFile image3);


    List<Home> read();

    Home modify(UUID id, Home homes);

    String delete(UUID id);

    List<Home> searchByCriteria(String criteria);
}
