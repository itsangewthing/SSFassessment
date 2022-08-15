package vttp2022.NUSISS.ssfAssessment.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.NUSISS.ssfAssessment.models.News;
import vttp2022.NUSISS.ssfAssessment.repositories.NewsRepository;

@Service
public class NewsService {
    // ADD URL 
    // https://min-api.cryptocompare.com/data/v2/news/?lang=EN&api_key=3145b8e02c2e33ae8ee4bc91cf50075c58a1fe35f6cc4bf9dc096b21e48f015f
    //                                                         api_key
    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news";

    // KEY INJECT
    @Value ("{API_KEY}")
    private String key;

    @Autowired
    private NewsRepository newsRepo;
    
///////////////// REPO
//////////////// API I <---- RETRIEVE LATEST NEWS ARTICLES 
    public List<News> getArticles(String feeds, String categories){
        // System.out.printf(">>>article: %s \n", getArticles(null));
        String encodedArticles = URLEncoder.encode(feeds, StandardCharsets.UTF_8);
        Optional<String> optt = newsRepo.get(encodedArticles);
        String payload;
        System.out.println("getting article from CRYPTOCOMPARE");
     

        /// 1---- GET URL WITH QUERY STRING
        String uri= UriComponentsBuilder.fromUriString(URL)
        .queryParam("API_KEY", key)
        .toUriString();
        /// 2---- CREATE GET REQUEST, GET URL 
        RequestEntity<Void> req = RequestEntity.get(uri).build();
            // Make the call to OpenWeatherApp
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;
        

        /// 3---- THROWS EXCEPTION if status code is not between 200 - 399

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e);
            return Collections.emptyList();
        }
        // Check status code
        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error status code is not 200\n");
            return Collections.emptyList();
        }


        // GET PAYLOAD
        payload = resp.getBody();
        System.out.println(">>>>>>> Payload: \n" + payload);


         // Convert payload into JsonObject
        // Convert string to a Reader
        Reader strReader = new StringReader(payload);

        // Create a JsonReader from reader
        JsonReader jsonReader = Json.createReader(strReader);

       // Read and save the payload as Json Object
       JsonObject articlesResult = jsonReader.readObject();
       // should tally with the object name from api
            JsonArray articlesDATA = articlesResult.getJsonArray("Data");

            List<News> list = new LinkedList<>();
            for (int i = 0; i < articlesDATA.size(); i++) {
            // loop through the top _ coins
            JsonObject jo = articlesDATA.getJsonObject(i);

            list.add(News.create(i, jo));
            }
            System.out.println("Latest News Articles List: " + list);
            return list;


}

//////////////// SAVE ARTICLE



}
