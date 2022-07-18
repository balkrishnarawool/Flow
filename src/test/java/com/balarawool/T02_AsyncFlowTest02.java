package com.balarawool;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class T02_AsyncFlowTest02 {

    @Test
    public void testSimpleFlow() {
        var customer = Customer.of("Customer001");
        var balance1 =
                getAccountForCustomer(customer)
                        .thenCompose(this::getBalanceForAccount)
                        .join();

        var balance2 = Flow
                .startWith(customer)
                .andThenAsync(this::getAccountForCustomer)
                .andThenAsync(this::getBalanceForAccount)
                .done()
                .join();

        assertEquals(balance1, balance2, "Balances are not same");
    }

    CompletableFuture<Account> getAccountForCustomer(Customer c) {
        return c.id.equals("Customer001") ? CompletableFuture.completedFuture(Account.of("Account001")) : null;
    }

    CompletableFuture<Balance> getBalanceForAccount(Account a) {
        return a.id.equals("Account001") ? CompletableFuture.completedFuture(Balance.of("Balance001")) : null;
    }

    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Customer { String id; }
    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Account { String id; }
    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Balance { String id; }

}