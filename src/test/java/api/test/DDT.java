package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDT {
	
	
	
	//post req multiple users
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String email, String password,String  phone  )
	

	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByUserName(String userName)
	{
		
		Response response=UserEndPoints.deleteUser(userName);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
