package com.example.aaahome.service.search;

import com.example.aaahome.mapper.GISMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class Search {
    private final GISMapper gisMapper;

    public Search(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    @RequestMapping("search/*")
    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String name = request.getRequestURI().substring(8);
        final Integer[] result = gisMapper.getLikeName(name);
        final String json = new Gson().toJson(new SearchResult(result));
        response.getWriter().write(json);
    }

}
class SearchResult{
    int[] ids=new int[10];

    public SearchResult(Integer[] lis) {
        for (int i = 0; i < lis.length; i++) {
            ids[i]=lis[i];
        }
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}
