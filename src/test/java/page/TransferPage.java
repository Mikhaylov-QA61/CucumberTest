package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement head = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=cation-cancel]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        head.shouldBe(visible);
    }

    public void makeTransfer(String transferredAmount, DataHelper.InfoCard infoCard){
        amount.setValue(transferredAmount);
        from.setValue(infoCard.getNumberCard());
        transferButton.click();
    }
}
