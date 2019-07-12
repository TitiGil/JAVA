/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;

/**
 *
 * @author amg
 */
public class Decoder {
    static String str="{\n" +
"   \"pageInfo\": {\n" +
"         \"pageName\": \"abc\",\n" +
"         \"pagePic\": \"http://example.com/content.jpg\"\n" +
"    },\n" +
"    \"posts\": [\n" +
"         {\n" +
"              \"post_id\": \"123456789012_123456789012\",\n" +
"              \"actor_id\": \"1234567890\",\n" +
"              \"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\",\n" +
"              \"nameOfPersonWhoPosted\": \"Jane Doe\",\n" +
"              \"message\": \"Sounds cool. Can't wait to see it!\",\n" +
"              \"likesCount\": \"2\",\n" +
"              \"comments\": [],\n" +
"              \"timeOfPost\": \"1234567890\"\n" +
"         }\n" +
"    ]\n" +
"}";
    static JSONObject jo=new JSONObject(str);
    public static void test(){
        JSONArray ja=jo.getJSONArray("posts");
        
        System.err.println(ja.getJSONObject(0).getString("post_id"));
        
        }
        
        
    }

