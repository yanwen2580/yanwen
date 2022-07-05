package cn.itrip.search.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.Page;
import cn.itrip.search.solr.SolrService;
import cn.itrip.search.vo.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SearchItripHotelListByHotCity {
    @Resource
    private SolrService service;
    @PostMapping("api/hotellist/searchItripHotelListByHotCity")
    public Dto searchItripHotelListByHotCity(@RequestBody Map<String,String> pram){
        System.out.println(pram.get("cityId"));
        System.out.println(pram.get("count"));
        return DtoUtil.returnDataSuccess(service.searchItripHotelListByHotCity(pram.get("cityId"),pram.get("count")));
    }
    @PostMapping("api/hotellist/searchItripHotelPage")
    public Dto searchItripHotelPage(@RequestBody Map<String,String> pram){
        Page page=new Page();
        page.setPageSize(2);
        page.setBeginPos(0);
        if (pram.get("pageNo")==null){
            page.setCurPage(1);
        }else {
            page.setCurPage(Integer.parseInt(pram.get("pageNo")));
        }
        page.setTotal(service.searchItripHotelCount(pram.get("destination")));
      return DtoUtil.returnDataSuccess(service.searchItripHotelPage(pram.get("destination"),page));
    }
    public static void main(String[] args) {
        try {
            HttpSolrClient client=new HttpSolrClient("http://localhost:9080/solr/hotel/");
            client.setParser(new XMLResponseParser());
            SolrQuery query=new SolrQuery();
            query.setQuery("keyword:早餐");
            query.setStart(0);
            query.setRows(10);
            QueryResponse response= client.query(query);
            List<ItripHotelVO> beans = response.getBeans(ItripHotelVO.class);
            beans.forEach(System.out::println);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
