package day_8;
/*
chaining mean every request interact with other request by:
-catch variables from a request and use it in another requests
-if all request in the same class (explain how in day_2 package )
-or every request has own class lets create class and explain how...
-after catch the variable you want to pass in another request
-can pass variables from a request to another by user testNg feature :-
-by pass (ITestContext context) as variable for all requests method
-then inside method of first request set variables as global variable by write context.setAttribute("AttributeKey",value)
-then call this global variable in side another requests by write context.setAttribute("AttributeKey)
and pass in local variable as int id = context.getAttribute("AttributeKey)  >> this need casting from object to ex integer by:
                                                                          int id = (int) context.getAttribute("AttributeKey);
-then execute the all request by xml file in to apply the chaining (can not execute each request individual) by to ways:-
1- execute in the test level when use context.setAttribute("AttributeKey",value) and  int id = (int) context.getAttribute("AttributeKey)
2- execute in the suit level when use context.getSuite.setAttribute("AttributeKey",value) and  int id = (int) context.getSuite.getAttribute("AttributeKey);

- note if use second way that execute in the suit level you can execute well also in first way that execute in test level
as in xml file the suite is high level contain test

 */

public class Chaining {
}
