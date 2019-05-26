package com.framework.api.utility.common;

import static io.restassured.RestAssured.given;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Api {
	Response resp;
	RequestSpecification requestSpec;
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	BasicAuthScheme auth;

	public void apiBasicAuth(String username, String password) {
		auth = new BasicAuthScheme();
		auth.setUserName(username);
		auth.setPassword(password);
		requestBuilder.setAuth(auth);
	}

	public void setHeaders(String headerName, String headerValue) {
		requestBuilder.addHeader(headerName, headerValue);
	}

	public Response getRequest(String apiURL) {
		requestSpec = requestBuilder.build();
		resp = given().
				spec(requestSpec).
			   when().
			   	get(apiURL).
			   then().
			   	assertThat().
			   		statusCode(200).
			   		contentType(ContentType.JSON).
			   	extract().
			   	response();
		return resp;
	}

	public Response postRequest(String apiURL, String body) {
		requestBuilder.setBody(body);
		requestSpec = requestBuilder.build();
		resp = given().spec(requestSpec).when().post(apiURL);
		return resp;
	}

}
