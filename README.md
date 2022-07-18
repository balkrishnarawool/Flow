# Flow

Flow helps you write readable Java.

For example,

    var balance = getBalanceForAccount(getAccountForCustomer(customer));

becomes

    var balance = Flow
                .startWith(customer)
                .andThen(this::getAccountForCustomer)
                .andThen(this::getBalanceForAccount)
                .done();

For more scenarios, see test classes.

