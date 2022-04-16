package javabrains.io.beta;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javabrains.io.beta.authors.Author;
import javabrains.io.beta.authors.AuthorRepository;
import javabrains.io.beta.connections.DataStaxProperties;



@SpringBootApplication
@EnableConfigurationProperties(DataStaxProperties.class)
public class BetterReadsDataLoaderApplication {
	@Lazy
	private  AuthorRepository authorRepository;
	@Autowired
	private Author author;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
		
	}
	
	@PostConstruct
	public  void prepareTable() {
		author.setId("id");
		author.setName("Robinhood");
		author.setParentName("pen name");
		System.out.println("Cassandra i am coming");
		authorRepository.save(author);
	}
	
	
	
	@Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
