package br.com.connect.service;

import org.springframework.ai.azure.openai.AzureOpenAiImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final AzureOpenAiImageModel imageModel;

    public ImageService(AzureOpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt,
                                       String quality,
                                       Integer n,
                                       Integer height,
                                       Integer width) {

        ImageResponse imageResponse = imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width).build()));

        //ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt));

//        ImageResponse imageResponse = imageModel.call(
//                new ImagePrompt("A light cream colored mini golden doodle",
//                        OpenAiImageOptions.builder()
//                                .quality("hd")
//                                .N(4)
//                                .height(1024)
//                                .width(1024).build()));



        return imageResponse;
    }
}
