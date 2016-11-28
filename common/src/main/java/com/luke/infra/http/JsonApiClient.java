//package com.luke.infra.http;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
//import com.luke.infra.http.model.Tasks;
//
//import retrofit2.Retrofit;
//
//public class JsonApiClient {
//	public static void doRequest() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		Retrofit retrofit = new Retrofit.Builder()
//		        .baseUrl("https://yourapi")
//		        .addConverterFactory(new JSONAPIConverterFactory(objectMapper, Tasks.class))
//		        .build();
//
//		// Create service using service interface
//
//		MyBooksService booksService = retrofit.create(MyBooksService.class);
//	}
//}
