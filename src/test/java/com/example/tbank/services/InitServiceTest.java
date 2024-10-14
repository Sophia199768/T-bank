package com.example.tbank.services;

import com.example.tbank.models.Category;
import com.example.tbank.models.City;
import com.example.tbank.repository.CategoryRepository;
import com.example.tbank.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private InitService initService;

    @Test
    public void onApplicationReady_ShouldUpdateRepository_whenRequestSuccess() {
        City city = new City("","");
        Category category = new Category(1, "", "");

        City[] cities = new City[]{city};
        Category[] categories = new Category[]{category};

        when(restTemplate.getForObject("https://kudago.com/public-api/v1.4/" + "locations/", City[].class)).thenReturn(cities);
        when(restTemplate.getForObject("https://kudago.com/public-api/v1.4/" + "event-categories/", Category[].class)).thenReturn(categories);

        initService.onApplicationReady();

        verify(categoryRepository).create(category);
        verify(locationRepository).create(city);
    }
}