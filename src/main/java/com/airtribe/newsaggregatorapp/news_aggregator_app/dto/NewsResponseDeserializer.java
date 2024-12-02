package com.airtribe.newsaggregatorapp.news_aggregator_app.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// To deserialize NewsResponse
public class NewsResponseDeserializer extends JsonDeserializer<NewsResponse> {
    @Override
    public NewsResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        NewsResponse response = new NewsResponse();

        if (node.has("totalArticles")) {
            response.setTotalArticles(node.get("totalArticles").asInt());
        }

        if (node.has("articles") && node.get("articles").isArray()) {
            List<Article> articles = new ArrayList<>();
            for (JsonNode articleNode : node.get("articles")) {
                Article article = p.getCodec().treeToValue(articleNode, Article.class);
                articles.add(article);
            }
            response.setArticles(articles);
        } else {
            response.setArticles(new ArrayList<>()); // Default empty list
        }

        return response;
    }
}
