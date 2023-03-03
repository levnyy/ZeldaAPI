package com.zelda.ZeldaAPI.controllers;

import com.zelda.ZeldaAPI.controller.service.LocationService;
import com.zelda.ZeldaAPI.controller.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.TimeZone;

import static com.zelda.ZeldaAPI.utils.TestDataUtil.getTestLocations;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.invocation.InvocationsFinder.getAllLocations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocationControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
public class LocationControllerTest {

    private static final String JSON_ALL_LOCATIONS = "{\"locations\":[{\"id\":1,\"name\":\"Test Location 1\"},{\"id\":2}]}";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void prepare() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void whenValidID_returnLocation() throws Exception {
        Integer locationID = 1;
        doReturn(locationID).when(locationService).findById(locationID);

        mockMvc.perform(get("/api/locations/" + locationID))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_ALL_LOCATIONS));
        verify(locationService).findById(locationID);

    }

    @Test
    public void checkGet_whenNoParam_getAllLocations() throws Exception {
        doReturn(getTestLocations()).when(locationService).findAll();

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_ALL_LOCATIONS));
    }

    @Test
    public void checkGet_whenValidName_thenLocationFound() throws Exception {
        String locationName = "Test Location 1";
        doReturn(locationName).when(locationService).findByName(locationName);

        mockMvc.perform(get("/api/locations/")
                        .contentType("application/json")
                        .queryParam("name", locationName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locations[0].name").value(locationName));
    }

    @Test
    public void checkGet_findByRewards() throws Exception {
        String locationName = "Test Location 1";
        doReturn(locationName).when(locationService).findByReward(locationName);

        mockMvc.perform(get("/api/locations/")
                       .contentType("application/json")
                       .queryParam("name", locationName))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.locations[0].name").value(locationName));
    }
}
