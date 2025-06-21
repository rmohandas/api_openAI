package br.com.connect.controller;

import br.com.connect.service.ChatService;
import br.com.connect.service.ImageService;
import br.com.connect.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

    private final ChatService chatService;
    private final RecipeService recipeService;
    private final ImageService  imageService;

    public GenerativeAIController(ChatService chatService, RecipeService recipeService, ImageService  imageService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt) {
        return chatService.getResponseWithOptions(prompt);
    }

    @PostMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "none") String dietaryRestrictions) {

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }
    @PostMapping("generate-image")
    public List<String> generateImage(@RequestParam String prompt,
                                     @RequestParam(defaultValue = "hd") String quality,
                                     @RequestParam(defaultValue = "1") Integer n,
                                     @RequestParam(defaultValue = "1024") Integer height,
                                     @RequestParam(defaultValue = "1024") Integer width) {

        ImageResponse response = imageService.generateImage(prompt, quality, n, height, width);
        List<String> imageUrls = response.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }
}
