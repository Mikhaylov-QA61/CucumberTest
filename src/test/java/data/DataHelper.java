package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Objects;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DataHelper {

    static Random random = new Random();

    private DataHelper() {
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static InfoCard getFirstCard() {
        InfoCard InfoCard = new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
        return InfoCard;
    }

    public static InfoCard getSecondCard() {
        InfoCard InfoCard = new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        return InfoCard;
    }

    public static InfoCard getCard(String cardNumber) {
        InfoCard InfoCard = null;

        if (Objects.equals(cardNumber, "5559 0000 0000 0001")) {
            InfoCard = new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
        }
        if (Objects.equals(cardNumber, "5559 0000 0000 0002")) {
            InfoCard = new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        }
        return InfoCard;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class InfoCard {
        String numberCard;
        String testId;
    }

    @Value
    public static class RegisteredUser {
        String login;
        String password;

    }
}
