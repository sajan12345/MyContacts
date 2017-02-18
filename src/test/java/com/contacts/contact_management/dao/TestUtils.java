/**
 * This source code is property of MyContacts Project Team.
 */
package com.contacts.contact_management.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;

import com.contacts.contact_management.enums.Gender;
import com.contacts.contact_management.model.Image;
import com.contacts.contact_management.model.Person;

/**
 * @author JavaTraining
 *
 */
public class TestUtils {

	public static Person getPersonObject(final String methodName) {
		Person personTransient = new Person();
		personTransient.setFirstName("FirstName " + methodName);
		personTransient.setLastName("LastName " + methodName);
		personTransient.setGender(Gender.Male);
		personTransient.setDob(Date.valueOf(LocalDate.now()));
		personTransient.setEmailId("EmailId@gmail.com");
		personTransient.setAlternateEmailId("AlternateEmailId@gmail.com");
		personTransient.setPhoneNumber(911114567890L);
		personTransient.setAlternatePhoneNumber(912224567890L);
		personTransient.setMaritalStatus(false);
		return personTransient;
	}

	public static Image getImageObject(final String methodName, final String imagePath) {
		Image imageTransient = new Image();
		imageTransient.setTag("Flower " + methodName);
		try {
			File file = new File(imagePath);
			imageTransient.setImage(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageTransient;
	}

}