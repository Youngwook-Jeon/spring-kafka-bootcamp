package com.project.young.api;

import com.project.young.entity.Commodity;
import com.project.young.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
@RequiredArgsConstructor
public class CommodityApi {

    private final CommodityService commodityService;

    @GetMapping( "/all")
    public List<Commodity> generateAllCommodities() {
        return commodityService.createDummyCommodities();
    }
}
