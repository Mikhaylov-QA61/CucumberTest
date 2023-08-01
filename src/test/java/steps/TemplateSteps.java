package steps;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import page.DashboardPage;
import page.LoginPage;
import page.TransferPage;
import page.VerificationPage;

public class TemplateSteps {
    public static DataHelper dataHelper;
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    public static TransferPage transferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginUser(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        var user = new DataHelper.RegisteredUser(login, password);
        verificationPage = loginPage.validLogin(user);
        var verificationCode = DataHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }
    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void makeTransfer(int amount, String numberCard, int indexCard) {
        int amountTransfer = amount;
        DataHelper.InfoCard selectedCard = dashboardPage.getCardNumber(indexCard);
        var transferPage = dashboardPage.selectCardToTransfer(selectedCard);
        var card = DataHelper.getCard(numberCard);
        transferPage.makeTransfer(String.valueOf(amountTransfer), card);
    }
    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int}")
    public void ActualBalance(int index, int balance) {
        int actualBalance = dashboardPage.getCardBalance(index);
        int expectedBalance = balance;
        Assert.assertEquals(expectedBalance,actualBalance);
    }
}
