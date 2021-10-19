package com.ishnit;

import ch.qos.logback.core.CoreConstants;
import com.ishnit.entity.Author;
import com.ishnit.entity.Book;
import com.ishnit.repository.AuthorRepository;
import com.ishnit.connection.DataStaxAstraProperties;
import com.ishnit.repository.BookRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
//@Configuration
//@ComponentScan("com.ishnit.*")
public class BooksreaddataloaderApplication {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Value("${datadump.location.author}")
    private String authorDumpLocation;

    @Value("${datadump.location.works}")
    private String worksDumpLocation;

    public static void main(String[] args) {
        SpringApplication.run(BooksreaddataloaderApplication.class, args);
    }

    private void initAuthors() {
        Path path = Paths.get(authorDumpLocation);
        try (Stream<String> lines = Files.lines(path)) {
            int[] counter = {0};
            List tempList = new ArrayList<Author>();

            lines.forEach(line -> {
                long totalSoFar = 0;
                //Read and parse each line
                String jasonString = line.substring(line.indexOf("{"));
                try {
                    JSONObject json = new JSONObject(jasonString);

                    //Construct Works Object
                    Author auth = new Author();
                    auth.setId(json.optString("key").replace("/authors/", ""));
                    auth.setName(json.optString("name"));
                    auth.setPersonalName(json.optString("personal_name"));
                    tempList.add(auth);

                    if (counter[0] % 2000 == 0) {
                        totalSoFar += counter[0];
                        //Persist with cassandra
                        authorRepository.saveAll(tempList);
                        System.out.println("Added Authors totalSoFar:-" + totalSoFar);
                        tempList.clear();
                        counter[0] = 1;
                    }
                    ++counter[0];
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            });
            if (!tempList.isEmpty()) {
                //Persist with cassandra
                authorRepository.saveAll(tempList);
                System.out.println("Saved Remaining Authors:-" + tempList.size());
            }

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    private void initWorks() {
        Path path = Paths.get(worksDumpLocation);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                //Read and parse each line
                String jasonString = line.substring(line.indexOf("{"));
                try {
                    JSONObject json = new JSONObject(jasonString);

                    //Construct Works Object
                    Book book = new Book();
                    book.setId(json.optString("key").replace("/works/", ""));
                    book.setName(json.optString("title"));
                    if (json.optJSONObject("description") != null) {
                        book.setDescription(json.optJSONObject("description").optString("value"));
                    }

                    if (json.optJSONObject("created") != null) {
                        book.setPublishedDate(LocalDate.parse(json.optJSONObject("created").optString("value"), dtf));
                    }

                    if (json.optJSONArray("covers") != null) {
                        List<String> coverIds = new ArrayList<String>();
                        for (int i = 0; i < json.optJSONArray("covers").length(); i++) {
                            coverIds.add(json.optJSONArray("covers").getString(i));
                        }
                        book.setCoverIds(coverIds);
                    }

                    if (json.optJSONArray("authors") != null) {
						List<String> authorIds = new ArrayList<String>();
						for (int i = 0; i < json.optJSONArray("authors").length(); i++) {
							String authorId = json.optJSONArray("authors").getJSONObject(i).getJSONObject("author").getString("key").replace("/authors/", "");
							authorIds.add(authorId);
						}
						book.setAuthorIds(authorIds);

						List<String> authorNames = authorIds.stream().map(id -> authorRepository.findById(id))
								.map(optionalAuthor -> {
									if (!optionalAuthor.isPresent()) return "Unknown Author";
									return optionalAuthor.get().getName();
								}).collect(Collectors.toList());
						book.setAuthorNames(authorNames);

						//Persist with cassandra
						bookRepository.save(book);
					}
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            });
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @PostConstruct
    public void start() {
        System.out.println("AUTH:" + authorDumpLocation + " ,Works:" + worksDumpLocation);
        //initAuthors();
        initWorks();
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
