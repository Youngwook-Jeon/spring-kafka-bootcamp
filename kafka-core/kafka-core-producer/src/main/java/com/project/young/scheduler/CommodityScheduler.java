package com.project.young.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.young.entity.Commodity;
import com.project.young.producer.CommodityProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommodityScheduler {

    private RestTemplate restTemplate = new RestTemplate();

    private final CommodityProducer producer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities() {
        List<Commodity> commodities = restTemplate.exchange(
                "http://localhost:8080/api/commodity/v1/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }
        ).getBody();
        commodities.forEach(t -> {
            try {
                producer.sendMessage(t);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
