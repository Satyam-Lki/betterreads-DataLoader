package javabrains.io.beta;

import java.nio.file.Path;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javabrains.io.beta.authors.Author;
import javabrains.io.beta.authors.AuthorRepository;
import javabrains.io.beta.connections.DataStaxProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxProperties.class)
public class BetterReadsDataLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(AuthorRepository authorRepository) {
		return args -> {
			Author author = new Author();
			author.setId("id");
			author.setName("Robinhood");
			author.setParentName("pen name");
			System.out.println("Cassandra i am coming");
			authorRepository.save(author);
		};
	}

	@Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
