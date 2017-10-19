package org.unsynchronized.jdeserialize.array;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unsynchronized.jdeserialize.jdeserialize;

public class ArrayDeserializationTest {

	private static final String FILE_OBJECT_WITH_ARRAYS = "array/object_with_arrays";

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
	}

	@Ignore("This method is not an actual test case. It can be used to create the serialized files for the actual test")
	@Test
	public void testSerializeADogWithNullArray() throws IOException {
		ObjectWithArrays objectWithArrays = new ObjectWithArrays(new String[] { "First, Second" }, new int[] {}, null);
		Path serialFile = Paths.get("src/test/resources/" + FILE_OBJECT_WITH_ARRAYS + ".ser");
		Files.createDirectories(serialFile.getParent());
		FileOutputStream fos = new FileOutputStream(serialFile.toAbsolutePath().toFile());
		try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(objectWithArrays);
			oos.flush();
		}
	}

	@Test
	public void testDeserializeADogWithNullArray() throws IOException, URISyntaxException {
		jdeserialize.main(new String[] { "src/test/resources/" + FILE_OBJECT_WITH_ARRAYS + ".ser" });

		String expectedOutput = new String(Files.readAllBytes(
				Paths.get(getClass().getClassLoader().getResource(FILE_OBJECT_WITH_ARRAYS + ".expectedOut").toURI())));

		assertThat(outContent.toString(), equalTo(expectedOutput));
	}

}
