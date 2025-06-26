package br.com.connect.controller;

import br.com.connect.service.ChatService;
import br.com.connect.service.ImageService;
import br.com.connect.service.RecipeService;
import br.com.connect.service.TravelAgentService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class GenerativeAIController {

    private final ChatService chatService;
    private final RecipeService recipeService;
    private final ImageService  imageService;
    private final TravelAgentService travelAgentService;

    public GenerativeAIController(
            ChatService chatService,
            RecipeService recipeService,
            ImageService  imageService,
            TravelAgentService travelAgentService) {

        this.chatService = chatService;
        this.recipeService = recipeService;
        this.imageService = imageService;
        this.travelAgentService = travelAgentService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt) {
        return chatService.getResponseWithOptions(prompt);
    }

    @GetMapping("/recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "none") String dietaryRestrictions) {

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

    @PostMapping("/generate-image")
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

    @GetMapping("/travel-creator")
    public String travelCreator(@RequestParam String country,
                                @RequestParam String personalInterests,
                                @RequestParam String tripDuration,
                                @RequestParam String budget) {

        return travelAgentService.createTravelAgent(country, personalInterests, tripDuration, budget);
    }
}
