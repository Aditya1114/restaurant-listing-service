package com.foodorder.restaurantlisting.controller;

import com.foodorder.restaurantlisting.dto.RestaurantDTO;
import com.foodorder.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantControllerTest {


    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllRestaurants() {
        List<RestaurantDTO> mockRestaurants = Arrays.asList(new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Description 1"),
                new RestaurantDTO(2, "Restaurant 2", "Address 2", "city 2", "Description 2")
        );
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);
        ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());
        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant() {
        RestaurantDTO mockRestaurant = new RestaurantDTO(2, "Restaurant 2", "Address 2", "city 2", "Description 2");
        when(restaurantService.addRestaurantInDB(Mockito.any())).thenReturn(mockRestaurant);
        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurant);
    }

    @Test
    public void testFindRestaurantById(){
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the service
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the service behavior
        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.findRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
    }
}
