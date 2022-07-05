package cn.itrip.search.solr;

import cn.itrip.common.Page;
import cn.itrip.search.vo.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrService {
    public static final String URL="http://localhost:9080/solr/hotel/";
    public List searchItripHotelListByHotCity( String cityId, String count){
        List list =new ArrayList();
        try {
            HttpSolrClient solrClient=new HttpSolrClient(URL);
            solrClient.setParser(new XMLResponseParser());
            SolrQuery solrQuery=new SolrQuery();
            solrQuery.setStart(0);
            solrQuery.setQuery("cityId:"+cityId);
            solrQuery.setRows(Integer.valueOf(count));
            QueryResponse solrResponse=solrClient.query(solrQuery);
            list=solrResponse.getBeans(ItripHotelVO.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List list =new ArrayList();
        try {
            HttpSolrClient solrClient=new HttpSolrClient("http://localhost:9080/solr/hotel/");
            solrClient.setParser(new XMLResponseParser());
            SolrQuery solrQuery=new SolrQuery();
            solrQuery.setStart(0);
            solrQuery.setQuery("cityId:"+3657);
            solrQuery.setQuery("keyword:早餐");
            solrQuery.setRows(Integer.valueOf(6));
            QueryResponse solrResponse=solrClient.query(solrQuery);
            list=solrResponse.getBeans(ItripHotelVO.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
     }

    public Page searchItripHotelPage(String destination,Page page) {
                List list =new ArrayList();
        try {
            HttpSolrClient solrClient=new HttpSolrClient("http://localhost:9080/solr/hotel/");
            solrClient.setParser(new XMLResponseParser());
            SolrQuery solrQuery=new SolrQuery();
            solrQuery.setStart(0);
            solrQuery.setQuery("destination:"+destination);
            solrQuery.setStart((page.getCurPage()-1)*page.getPageSize());
            solrQuery.setRows(page.getPageSize());
            QueryResponse solrResponse=solrClient.query(solrQuery);
            page.setTotal(new Long(solrResponse.getResults().getNumFound()).intValue());
            list=solrResponse.getBeans(ItripHotelVO.class);
            page.setRows(list);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
        }
    public   Integer  searchItripHotelCount(String destination) {
        List list =new ArrayList();
         try {
            HttpSolrClient solrClient=new HttpSolrClient("http://localhost:9080/solr/hotel/");
            solrClient.setParser(new XMLResponseParser());
            SolrQuery solrQuery=new SolrQuery();
            solrQuery.setStart(0);
            solrQuery.setQuery("destination:"+destination);
             QueryResponse solrResponse=solrClient.query(solrQuery);
            list=solrResponse.getBeans(ItripHotelVO.class);
         } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.size();
    }
    }

