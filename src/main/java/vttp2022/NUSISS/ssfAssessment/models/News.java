package vttp2022.NUSISS.ssfAssessment.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class News {

    /// variables
    private static String id; // news article ID 
    private static String publishedDate; //time stamp
    private static String title;
    private static String url; // clickable link
    private static String imageURL;
    private static String body; // article summary
    private static String tags;
    private static String categories;
    private String save;
    
////////////// GETTERS & SETTERS //////////////     
    public String getId() { return id;}
    public void setId(String id) { News.id = id; }
    
    public String getPublished_On() { return publishedDate; }
    public void setPublished_On(String publishedDate) { News.publishedDate = publishedDate; }
   
    public String getTitle() { return title; }
    public void setTitle(String title) {  News.title = title; }
   
    public String getUrl() { return url; }
    public void setUrl(String url) { News.url = url; }
    
    public String getImageURL() { return imageURL;}
    public void setImageURL(String imageURL) {  News.imageURL = imageURL;   }
    
    public String getBody() {  return body;}
    public void setBody(String body) { News.body = body;   }
    
    public String getTags() { return tags;   }
    public void setTags(String tags) { News.tags = tags;   }
   
    public String getCategories() {   return categories; }
    public void setCategories(String categories) { News.categories = categories; }

    public String getSave() { return save;  }
    public void setSave(String save) { this.save = save; }

////////////// Convert from JsonObject to Model object ---- (API: Lastest News Articles) ////////    
    
    public static News create(JsonObject coinNewsJo, JsonObject newsData){
       //INSTANTIATE NEWS ARTICLE
        News cn = new News();

        cn.setId(coinNewsJo.toString());
        cn.setPublished_On(coinNewsJo.getString(publishedDate));
        cn.setTitle(coinNewsJo.getString(title));
        cn.setUrl(coinNewsJo.getString(url));
        cn.setImageURL(imageURL);
        cn.setBody(body);
        cn.setTags(tags);
        cn.setCategories(categories);
        return cn;
    }

    // JSON Object for save 

        public JsonObjectBuilder toJson() {
            add("title", title)
            .add("url", url)
            .add("imageURL", imageURL)
            .add("body", body)
            .add("tags", tags)
            .add("categories",categories);
            return Json.createObjectBuilder();
        }
        private JsonObjectBuilder add(String string, String title2) {
            return null;
        }
        public static News create(int i, JsonObject jo) {
            return null;
        }
   
        
    }

