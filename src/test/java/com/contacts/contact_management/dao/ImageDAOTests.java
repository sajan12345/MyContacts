/**
 * This source code is property of MyContacts Project Team.
 */
package com.contacts.contact_management.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.contacts.contact_management.model.Image;
import com.contacts.contact_management.model.Person;
import com.contacts.utils.MyLogger;

/**
 * 
 * @author Sandeep
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageDAOTests {

	private static Logger log;

	@Autowired
	private ImageDAO imageDAO;

	@Autowired
	private PersonDAO personDAO;

	@BeforeClass
	public static void setUp() {
		log = MyLogger.getLogger();
	}

	@Ignore
	@Test
	public void createImage() {
		Image imageTransient = TestUtils.getImageObject("createImage", "D:\\images\\image1.jpg");
		Person personTransient = TestUtils.getPersonObject("createImage");
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);

		imageTransient.setPerson(personPersisted);
		Image imagePersisted = imageDAO.save(imageTransient);

		assertNotNull(imagePersisted);
		assertNotNull(imagePersisted.getPerson());
		assertEquals(imagePersisted.getTag(), "Flower createImage");
	}

	@Ignore
	@Test
	public void getImageById() {
		Image imageTransient = TestUtils.getImageObject("getImageById", "D:\\images\\image2.jpg");
		Person personTransient = TestUtils.getPersonObject("getImageById");
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);

		imageTransient.setPerson(personPersisted);
		Image imagePersisted = imageDAO.save(imageTransient);
		Image imageFromDB = imageDAO.findOne(imagePersisted.getId());

		assertNotNull(imageFromDB);
		assertNotNull(imageFromDB.getImage());
		assertEquals(imageFromDB.getTag(), "Flower getImageById");

		File outFile = new File("D:\\images\\outputimage2.jpg");
		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile))) {
			bufferedOutputStream.write(imageFromDB.getImage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void updateImage() {
		Image imageTransient = TestUtils.getImageObject("updateImage", "D:\\images\\image3.jpg");
		Person personTransient = TestUtils.getPersonObject("updateImage");
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);

		imageTransient.setPerson(personPersisted);
		Image imagePersisted = imageDAO.save(imageTransient);

		assertNotNull(imagePersisted);
		assertNotNull(imagePersisted.getPerson());
		assertEquals(imagePersisted.getTag(), "Flower updateImage");

		Image imageFromDB = imageDAO.findOne(imagePersisted.getId());

		assertNotNull(imageFromDB);
		assertNotNull(imageFromDB.getPerson());
		assertEquals(imageFromDB.getTag(), "Flower updateImage");

		try {
			File file = new File("D:\\images\\image4.jpg");
			imagePersisted.setImage(Files.readAllBytes(file.toPath()));
			imagePersisted.setTag("Flower updateImage updated");
			Image image3 = imageDAO.save(imagePersisted);

			assertNotNull(image3);
			assertEquals(image3.getTag(), "Flower updateImage updated");

			File outFile = new File("D:\\images\\outputimage4.jpg");
			try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile))) {
				bufferedOutputStream.write(image3.getImage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void deleteImage() {
		Image imageTransient = TestUtils.getImageObject("deleteImage", "D:\\images\\image1.jpg");
		Person personTransient = TestUtils.getPersonObject("deleteImage");
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);

		imageTransient.setPerson(personPersisted);
		Image imagePersisted = imageDAO.save(imageTransient);

		assertNotNull(imagePersisted);
		assertNotNull(imagePersisted.getPerson());
		assertEquals(imagePersisted.getTag(), "Flower deleteImage");

		imageDAO.delete(imagePersisted.getId());
		log.info("Image deleted " + imagePersisted.getId());
	}

}