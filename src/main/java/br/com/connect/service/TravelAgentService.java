package br.com.connect.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TravelAgentService {

    private final ChatModel chatModel;

    public TravelAgentService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createTravelAgent(String country,
                             String personalInterests,
                             String tripDuration,
                             String budget) {

        var template = """
                Você é um agente de viagens virtual especializado em turismo e gastronomia internacional.
                Com base nas informações fornecidas abaixo, gere uma resposta personalizada, atrativa e informativa para o cliente, contendo:
                1. Principais pontos turísticos e experiências imperdíveis no destino.
                2. Sugestões de atividades baseadas nos interesses do cliente (gastronomia, cultura, aventura, natureza, compras etc.).
                3. Pratos típicos e recomendações gastronômicas locais (inclua breves descrições).
                4. Dicas úteis relacionadas à duração da viagem (ex: quantos dias ficar em cada local, o que priorizar).
                5. Dicas adaptadas ao orçamento do cliente (viagem econômica, intermediária ou de luxo).
                6. Dicas culturais rápidas ou curiosidades.
                
                Use linguagem envolvente, clara e amigável.
                
                ---
                
                Informações fornecidas pelo cliente:
                Destino: { country }
                Interesses pessoais: { personalInterests }
                Duração da viagem: { tripDuration }
                Orçamento: { budget }
                
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "country", country,
                "personalInterests", personalInterests,
                "tripDuration", tripDuration,
                "budget", budget
        );

        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }

}
