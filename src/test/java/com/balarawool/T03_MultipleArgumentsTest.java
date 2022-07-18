package com.balarawool;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class T03_MultipleArgumentsTest {

    @Test
    public void testSimpleFlow() {
        var customer = Customer.of("Customer001");

        var permission = new Permission();
        var balance1 = getBalanceForAccount(getAccountForCustomer(customer, permission));

        var balance2 = Flow
                .startWith(customer)
                .andThenAdd(permission)
                .andThen(this::getAccountForCustomer)
                .andThen(this::getBalanceForAccount)
                .done();

        assertEquals(balance1, balance2, "Balances are not same");
    }

    Account getAccountForCustomer(Customer c, Permission p) {
        return c.id.equals("Customer001") ? Account.of("Account001") : null;
    }

    Balance getBalanceForAccount(Account a) {
        return a.id.equals("Account001") ? Balance.of("Balance001") : null;
    }

    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Customer { String id; }
    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Account { String id; }
    @AllArgsConstructor(staticName = "of") @EqualsAndHashCode static class Balance { String id; }
    static class Permission { }

}