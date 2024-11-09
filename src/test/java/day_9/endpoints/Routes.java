package day_9.endpoints;
/*
Swagger url >>>         https://petstore.swagger.io/
Create user (Post) >>>  https://petstore.swagger.io/v2/user
Get user (Get) >>>      https://petstore.swagger.io/v2/user/{username}
Update user (Put) >>>   https://petstore.swagger.io/v2/user/{username}
Delete user(Delete) >>> https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {
    public static String Base_url ="https://petstore.swagger.io/v2";

    //user module
    public static String Post_url= Base_url+"/user";
    public static String Get_url= Base_url+"/user/{username}";
    public static String Update_url= Base_url+"/user/{username}";
    public static String Delete_url= Base_url+"/user/{username}";

    //ex: pets module
    //urls of pets model
}
