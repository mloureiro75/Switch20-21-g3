package switchtwentytwenty.project.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.DTOs.MoneyValue;
import switchtwentytwenty.project.domain.DTOs.input.AddBankAccountDTO;
import switchtwentytwenty.project.domain.DTOs.input.AddCashAccountDTO;
import switchtwentytwenty.project.domain.DTOs.input.AddCreditCardAccountDTO;
import switchtwentytwenty.project.domain.model.Application;
import switchtwentytwenty.project.domain.model.Family;
import switchtwentytwenty.project.domain.model.FamilyMember;
import switchtwentytwenty.project.domain.model.accounts.Account;
import switchtwentytwenty.project.domain.services.AccountService;
import switchtwentytwenty.project.domain.services.FamilyService;
import switchtwentytwenty.project.domain.utils.CurrencyEnum;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GetAccountBalanceControllerTest {

    Application ffmApp = new Application();
    AddFamilyController addFamilyController = new AddFamilyController(ffmApp);
    AddFamilyAdministratorController addFamilyAdministratorController = new AddFamilyAdministratorController(ffmApp);
    AddBankAccountController addBankAccountController = new AddBankAccountController(ffmApp);
    AddBankSavingsAccountController addBankSavingsAccountController = new AddBankSavingsAccountController(ffmApp);
    AddCreditCardAccountController addCreditCardAccountController = new AddCreditCardAccountController(ffmApp);
    CreatePersonalCashAccountController createPersonalCashAccountController = new CreatePersonalCashAccountController(ffmApp);
    GetAccountBalanceController getAccountBalanceController = new GetAccountBalanceController(ffmApp);

    String cc = "000000000ZZ4";
    String name = "Diogo";
    Date date = new Date(1990, 8, 26);
    Integer numero = 919999999;
    String email = "josediogoccbr@gmail.com";
    int nif = 212122233;
    String rua = "Rua Nossa";
    String codPostal = "4444-555";
    String local = "Zinde";
    String city = "Porto";
    String relacao = "filho";


    double bankAccBalance = 0.6;
    double savingsAccBalance = 15;
    double cashAccBalance = 5;
    double negativeBalance = -2;
    double interestRate = 5;
    String currentAccName = "Current";
    String savingsAccName = "Savings";
    String cashAccName = "Cash";
    String creditCardAccName = "Credit Card";
    MoneyValue accountBalance = new MoneyValue(bankAccBalance, CurrencyEnum.EURO);

    @BeforeEach
    void setUp() {

        addFamilyController.addFamily("Ribeiro");
        addFamilyAdministratorController.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, 1);
        AddBankAccountDTO addBankAccountDTO = new AddBankAccountDTO(bankAccBalance, currentAccName, cc, 1);
        addBankAccountController.addBankAccount(addBankAccountDTO);
        addBankSavingsAccountController.addBankSavingsAccount(1, cc, savingsAccName, savingsAccBalance, interestRate);
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(cc, 1, creditCardAccName, 5000.00, 100.0, 20.0, CurrencyEnum.EURO);
        addCreditCardAccountController.addCreditCardAccountToFamilyMember(addCreditCardAccountDTO);
        AddCashAccountDTO addCashAccountDTO = new AddCashAccountDTO(cashAccBalance, cashAccName, cc, 1);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTO);
    }

    @Test
    void getBankAccountBalanceTest1_Success() {
        MoneyValue expected = new MoneyValue(0.6, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 1);

        Assertions.assertEquals(expected, result);
        Assertions.assertNotSame(expected, result);
    }
    @Test
    void getBankAccountBalanceTest2_NotEquals() {

        MoneyValue expected = new MoneyValue(0.9, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 1);

        Assertions.assertNotEquals(expected, result);
    }
    @Test
    void getSavingsAccountBalanceTest1_Success() {
        MoneyValue expected = new MoneyValue(15.0, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 2);

        Assertions.assertEquals(expected, result);
        Assertions.assertNotSame(expected, result);
    }
    @Test
    void getSavingsAccountBalanceTest2_NotEquals() {
        MoneyValue expected = new MoneyValue(0.9, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 2);

        Assertions.assertNotEquals(expected, result);
    }

    @Test
    void getCreditCardAccountBalanceTest1_Success() {
        MoneyValue expected = new MoneyValue(100.0, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 3);

        Assertions.assertEquals(expected, result);
        Assertions.assertNotSame(expected, result);
    }
    @Test
    void getCreditCardAccountBalanceTest2_NotEquals() {
        MoneyValue expected = new MoneyValue(0.9, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 3);

        Assertions.assertNotEquals(expected, result);
    }

    @Test
    void getCashAccountBalanceTest1_Success() {
        MoneyValue expected = new MoneyValue(5.0, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 4);

        Assertions.assertEquals(expected, result);
        Assertions.assertNotSame(expected, result);
    }
    @Test
    void getCashAccountBalanceTest2_NotEquals() {
        MoneyValue expected = new MoneyValue(0.9, CurrencyEnum.EURO);

        MoneyValue result = getAccountBalanceController.getAccountBalance(1, cc, 4);

        Assertions.assertNotEquals(expected, result);
    }
    //adicionar testes para moneyvalue quando estiver tudo adicionado

}