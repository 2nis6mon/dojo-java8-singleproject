package org.dojo.java8.exercise5;


import org.dojo.java8.model.User;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileOperationsTest {

    @Test
    public void should_parse_user_csv() {
        List<User> users = FileOperations.loadUsersFromCsv(getFileFromPath("users.csv"));

        assertThat(users).hasSize(1000);
        User firstUser = users.get(0);
        assertThat(firstUser.getTitle()).isEqualTo("Mme");
        assertThat(firstUser.getFirstname()).isEqualTo("Colette");
        assertThat(firstUser.getLastname()).isEqualTo("Bernard");
        assertThat(firstUser.getLogin()).isEqualTo("colettebernard");
        assertThat(firstUser.getPassword()).isEqualTo("9876543210");
        assertThat(firstUser.getExpireDate()).isEqualTo(LocalDate.of(2000, 12, 9));
    }

    @Test
    public void should_find_file_path_by_filename() throws IOException {
        Path fileWithName = FileOperations.findRecursivelyFileByName(".", "FileOperations.java");

        assertThat(fileWithName).isEqualTo(Paths.get("./src/main/java/org/dojo/java8/exercise5/FileOperations.java"));
    }

    @Test(expected = FileNotFoundException.class)
    public void should_not_find_file_path_by_filename_when_file_not_exist() throws IOException {
        Path fileWithName = FileOperations.findRecursivelyFileByName(".", "UserParser123.java");

        assertThat(fileWithName).isNull();
    }

    private static Path getFileFromPath(String csvPath) {
        try {
            return Paths.get(FileOperations.class.getClassLoader().getResource(csvPath).toURI());
        } catch (URISyntaxException e) {

            return null;
        }
    }

}