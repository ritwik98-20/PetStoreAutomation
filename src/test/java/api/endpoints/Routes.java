package api.endpoints;

/*
 Swaggar URI-https://petstore.swagger.io/
 create user-post-https://petstore.swagger.io/v2/user
 Get user-Get-https://petstore.swagger.io/v2/user/{username}
 Update user-PUT-https://petstore.swagger.io/v2/user/{username}
 DeleteUser-Delete-https://petstore.swagger.io/v2/user/{username}
 
 */
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//userModel
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//postModel
	//Create-post-https://petstore.swagger.io/v2/store/order
	
	//storeModel
	
}
