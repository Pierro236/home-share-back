package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Home;


import java.util.List;
import java.util.UUID;




public interface HomeService {
    Home create(Home homes);

    List<Home> read();

    Home modify(UUID id, Home homes);

    String delete(UUID id);
}
