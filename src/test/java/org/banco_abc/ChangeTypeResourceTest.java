package org.banco_abc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class ChangeTypeResourceTest {
    @Test
    void queryChangeTypeTest() {
        given()
                .queryParam("dni", "12345678")
                .when().get("/change-type")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }
}