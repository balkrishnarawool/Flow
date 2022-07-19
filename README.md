# Flow

Flow helps you write readable Java.

For example,
```java
var balance1 = getBalanceForAccount(getAccountForCustomer(customer));
```
becomes
```java
var balance2 = Flow
            .startWith(customer)
            .andThen(this::getAccountForCustomer)
            .andThen(this::getBalanceForAccount)
            .done();
```
For more scenarios, see test classes.

