package org.mql.ai;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.util.Map;

@Path("/translate")
@RolesAllowed("USER")
public class TranslateResource {

    private final Client client;

    public TranslateResource() {
        this.client = Client
        		.builder()
        		.apiKey("AIzaSyAI_LSSY_pKcAPGmYumh97EXyDSThw23lE")
        		.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> translate(Map<String, String> req) {
        String text = req.get("text");
        String targetLanguage = req.getOrDefault("targetLanguage", "Darija");

        try {
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.5-flash",
                    "You are a translator. Translate the following text to " + targetLanguage +
                            ". Return only the translated text without any additional explanation.\n\nText: " + text,
                            null
            );

            return Map.of("translation", response.text());

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Translation failed: " + e.getMessage());
        }
    }
}
