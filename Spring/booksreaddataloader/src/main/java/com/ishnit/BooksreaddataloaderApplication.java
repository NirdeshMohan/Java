package com.ishnit;

import com.ishnit.author.Author;
import com.ishnit.repository.AuthorRepository;
import com.ishnit.connection.DataStaxAstraProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
@Configuration
@ComponentScan("com.ishnit.*")
public class BooksreaddataloaderApplication {

	@Autowired AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BooksreaddataloaderApplication.class, args);
	}

	@PostConstruct
	public void start(){
		System.out.println("app started");
		Author auth = new Author();
		auth.setId("TEST");
		auth.setName("NIRDESH");
		auth.setPersonalName("NIRDESHMOHAN");
		authorRepository.save(auth);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties){
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
