package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    public SelenideElement heading = $("[data-test-id=dashboard]");
    public ElementsCollection listCard = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final String numberStart = "000";
    private final String numberFinish = ", ";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
     public int getCardBalance(int index){
        var text = listCard.get(index-1).getText();
        return extractBalance(text);
     }
    public TransferPage selectedCardToTransfer(int index){
        listCard.get(index-1).$(".button").click();
        return  new TransferPage();
        }
}
