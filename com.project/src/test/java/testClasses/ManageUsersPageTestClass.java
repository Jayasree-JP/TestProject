package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageClasses.HomePageClass;
import pageClasses.LoginPageClass;
import pageClasses.ManageUsersPageClass;

public class ManageUsersPageTestClass extends BaseClass {

	LoginPageClass lp;
	HomePageClass hp;
	ManageUsersPageClass up;
	
	@Test
	public void verifyTheManageUsersPageIsOpenWhileClickingOnUsers() {

		lp = new LoginPageClass(driver);
		hp = lp.login("admin", "123456");
		hp.clickOnEndTour();
		hp.clickUserManagementBtnInHomePage();
		up = hp.clickOnUsersOptionUnderUserMngmt();
		String actualRes = up.getTextOfManageUsersHeading();
		Assert.assertTrue(actualRes.contains("Manage"));

	}

	@Test
	public void verifyToAdd_aNewUser() {

		lp = new LoginPageClass(driver);
		hp = lp.login("admin", "123456");
		hp.clickOnEndTour();
		hp.clickUserManagementBtnInHomePage();
		up = hp.clickOnUsersOptionUnderUserMngmt();
		up.addUserInManageUsers("rathu", "rathumol@gmail.com", "rathu", "123456", "123456");
		up.searchTheAddedUserInSearchBoxAfterAddition("rathu");
		String actualRes = up.getTextOfNameFieldOfUserCameAfterSearchInFilter();
		String expectedRes = "rathu";
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualRes, expectedRes, "User not added successfully!!!");
		soft.assertAll();
		System.out.println("User rathu added successfully!!!");
	}

}
