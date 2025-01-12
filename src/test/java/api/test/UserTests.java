package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;
/*
{
"id": 0,
"username": "string",
"firstName": "string",
"lastName": "string",
"email": "string",
"password": "string",
"phone": "string",
"userStatus": 0
}
 */
public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload =new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
		logger.debug("debugging...");
		
		
	}
	@Test(priority=1)
	public void testCreateuser()
	{
		logger.info("******Creating User*******");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******User is created *******");
	}
	@Test(priority=2)
	public void testGetuserByName()
	{
		logger.info("******Getting UserName*******");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		response.then().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******user Name retrive*******");
	}
	
	@Test(priority=3)
	public void testUpdateUserByUsername()
	{
		logger.info("******Update UserName by UserName*******");
		//update the data using same payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().all();
		response.then().statusCode(200);
		response.then().log().body().statusCode(200);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// checking data after update
		Response afterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(afterupdate.getStatusCode(), 200);
		logger.info("******First Name, Lastname and Mail is updated*******");
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByUsername()
	{
		logger.info("******Delete UserNAme By Username*******");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body().statusCode(200);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******user is deleted*******");
	}
	@Test(priority=5)
	public void testGetuserByNameAfterDelete()
	{
		logger.info("******Getting UserName*******");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		response.then().statusCode(404);
		Assert.assertEquals(response.getStatusCode(), 404);
		logger.info("******user Name notfound*******");
	}
	

}





















