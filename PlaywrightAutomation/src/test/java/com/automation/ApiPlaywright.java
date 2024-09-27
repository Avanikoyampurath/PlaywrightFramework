package com.automation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ApiPlaywright {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext requestContext = request.newContext();
        RequestOptions options = RequestOptions.create();

        String token = createToken(options, requestContext);
        String id = createBooking(options, requestContext);
        getBooking(requestContext, id);
        updateBooking(requestContext, options, id, token);
        deleteBooking(requestContext, options, id, token);

        playwright.close();
    }

    public static String createToken(RequestOptions options, APIRequestContext requestContext) {
        String body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        options.setData(body);
        options.setHeader("Content-Type", "application/json");

        APIResponse response = requestContext.post("https://restful-booker.herokuapp.com/auth", options);
        System.out.println("create token : " + response.status());

        String responseBody = response.text();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String token = jsonObject.get("token").getAsString();
        System.out.println("token : " + token);

        return token;

    }

    public static String createBooking(RequestOptions options, APIRequestContext requestContext) {
        String body = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        options.setData(body);
        options.setHeader("Content-Type", "application/json");

        APIResponse response = requestContext.post("https://restful-booker.herokuapp.com/booking", options);
        System.out.println("create booking : " + response.status());

        String responseBody = response.text();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String id = jsonObject.get("bookingid").getAsString();
        System.out.println("booking id : " + id);

        return id;
    }

    public static void getBooking(APIRequestContext requestContext, String id) {
        APIResponse response = requestContext.get("https://restful-booker.herokuapp.com/booking/" + id);
        System.out.println("get booking : " + response.status());
        System.out.println("booking details : \n" + response.text());
    }

    public static void updateBooking(APIRequestContext requestContext, RequestOptions options, String id, String token) {
        String body = "{\n" +
                "    \"firstname\" : \"Jack\",\n" +
                "    \"lastname\" : \"Green\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Dinner\"\n" +
                "}";
        options.setData(body);
        options.setHeader("Content-Type", "application/json");
        options.setHeader("Accept", "application/json");
        options.setHeader("Cookie", "token=" + token);
        APIResponse response = requestContext.put("https://restful-booker.herokuapp.com/booking/" + id, options);
        System.out.println("update booking : " + response.status());
        System.out.println("updated details : \n" + response.text());

    }

    public static void deleteBooking(APIRequestContext requestContext, RequestOptions options, String id, String token) {
        options.setHeader("Content-Type", "application/json");
        options.setHeader("Cookie", "token=" + token);
        APIResponse response = requestContext.delete("https://restful-booker.herokuapp.com/booking/" + id, options);
        System.out.println("delete booking : " + response.status());
        System.out.println(response.text());
    }
}
