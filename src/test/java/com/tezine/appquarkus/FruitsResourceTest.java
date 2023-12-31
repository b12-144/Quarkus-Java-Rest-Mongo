package com.tezine.appquarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitsResourceTest {

    @Test
    public void testGetFruitsEndpoint() {
        given()
          .when().get("/v1/fruits/get-fruits")
          .then()
             .statusCode(200);

        // given()
        //   .when().get("/fruits/get-fruits")
        //   .then()
        //      .statusCode(200)
        //      .body(is(""));
    }

}