package com.booking.hotel.controller;

import com.booking.hotel.entity.Hotel;
import com.booking.hotel.repository.HotelRepository;
import com.booking.hotel.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    private static Logger logger = LoggerFactory.getLogger(HotelController.class);

    @GetMapping("/hotels")
    public Flux<Hotel> fetchHotels() {
        logger.info("In method fetchHotels in Controller class ------------ 1");
        return Flux.create((emitter) -> {
            logger.info("In method fetchHotels in Controller class ------------ 2");
             hotelService.findHotels().whenComplete( (hotelList, exception) -> {
                 logger.info("In method fetchHotels in Controller class ------------ 3");
                if (exception == null) {
                    hotelList.forEach(emitter::next);
                    emitter.complete();
                } else {
                    emitter.complete();
                }
            });
        });
    }

    @GetMapping("/hotels/{id}")
    public Mono<Hotel> fetchHotelById(@PathVariable String id) {
        logger.info("In method fetchHotelById in Controller class ------------ 1");
        return Mono.fromFuture(hotelService.findHotelById(Integer.parseInt(id)));
    }

}