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
    public DataHelper.InfoCard getCardNumber(int index){
        var text = listCard.get(index-1).getText();
        return getSelectedCard(text);
    }
    public DataHelper.InfoCard getSelectedCard(String text){
         val start = text.indexOf(numberStart);
         val finish = text.indexOf(numberFinish);
         var value = text.substring(start + numberStart.length(), finish);

        DataHelper.InfoCard selectedCard = null;
        if (Objects.equals(value, "1")) {
            selectedCard = new DataHelper.InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
        }
        if (Objects.equals(value, "2")) {
            selectedCard = new DataHelper.InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        }
        return selectedCard;
     }

    public TransferPage selectCardToTransfer(DataHelper.InfoCard selectedCard) {
        listCard.findBy(attribute("data-test-id",selectedCard.getTestId())).$(".button").click();
        return new TransferPage();
    }
}
