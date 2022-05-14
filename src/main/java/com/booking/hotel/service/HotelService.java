package com.booking.hotel.service;

import com.booking.hotel.entity.Hotel;
import com.booking.hotel.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    private static Logger logger = LoggerFactory.getLogger(HotelRepository.class);

    @Async
    public CompletableFuture<Hotel> findHotelById(int hotelId){
        CompletableFuture<Hotel> future = CompletableFuture.supplyAsync(() -> {
            logger.info("In method findHotelById in service class");
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return hotelRepository.findById(hotelId);
        });
        return future;
    }
    @Async
    public CompletableFuture<List<Hotel>> findHotels(){

        CompletableFuture<List<Hotel>> future = CompletableFuture.supplyAsync( () -> {
            logger.info("In method findHotels in service class");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Hotel> hotelList = hotelRepository.findAll();
            return hotelList;
        });
         return future;

    }
}


