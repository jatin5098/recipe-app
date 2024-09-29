package com.example.recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RecipeService {

    @Value("${app_id}")
    private static final String app_id = "";

    @Value("${app_key}")
    private static final String app_key = "";

    @Autowired
    private RestTemplate restTemplate;

    public Object getRecipes(String cuisineType) {

        String url = "https://api.edamam.com/api/recipes/v2?type=public&" +
                "app_id=f289bb3b" + "&app_key=a82a9e4a110b6c510d19b182fa3eb4ef" +
                "&random=false&field=uri&field=label&field=image&field=source&field=url&field=cuisineType" +
                "&cuisineType=" + cuisineType;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getRecipesFromUrl(String url) {
        return restTemplate.getForObject(url, Object.class);
    }

}
