package com.example.recipe.apis;

import com.example.recipe.dto.RequestObjectMapper;
import com.example.recipe.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/recipes")
public class RecipeController {

    private static final Log log = LogFactory.getLog(RecipeController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<?> getAllRecipes(
            @RequestParam(value="cuisineType", defaultValue = "world", required = false)  String cuisineType
    ) {
        log.info("Fetching recipe details");
        return ResponseEntity.ok(recipeService.getRecipes(cuisineType));
    }

    @PostMapping("/url")
    public ResponseEntity<?> getAllRecipesByUrl(
            @RequestBody String url
    ) {
        log.info("Fetching recipe details from URL");
        try {
            ObjectMapper mapper = new ObjectMapper();
            RequestObjectMapper obj = mapper.readValue(url, RequestObjectMapper.class);
            return ResponseEntity.ok(recipeService.getRecipesFromUrl(obj.getUrl()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

