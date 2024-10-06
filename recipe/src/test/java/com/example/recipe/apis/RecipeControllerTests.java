package com.example.recipe.apis;

import com.example.recipe.services.RecipeService;
import com.example.recipe.utils.JSONUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles(profiles = "test")
public class RecipeControllerTests {

    @InjectMocks
    RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Test
    public void getAllRecipesTest() {
        Object mockResponse = new JSONUtils().getFakeResponseFromLocalJson("/recipes.json");
        when(recipeService.getRecipes(any())).thenReturn(mockResponse);
        ResponseEntity<?> response = recipeController.getAllRecipes(any());
        Mockito.verify(recipeService, Mockito.times(1)).getRecipes(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> resMap = (Map<String, Object>) response.getBody();
        assertEquals(resMap.get("count"), 10000);
    }
}
