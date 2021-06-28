package com.hari.spring.graphql.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hari.spring.graphql.dao.PersonRepository;
import com.hari.spring.graphql.entity.Person;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Value("classpath:person.graphqls")
	private Resource schemaResource;

	private GraphQL graphql;

	@PostConstruct
	public void loadSchema() throws IOException {

		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphql = GraphQL.newGraphQL(schema).build();

	}


	private RuntimeWiring buildWiring() {
		DataFetcher<List<Person>> fetcher1 = data -> {
			return (List<Person>) personRepository.findAll();
		};

		DataFetcher<Person> fetcher2 = data -> {
			return personRepository.findByEmail(data.getArgument("email"));
		};
		return RuntimeWiring.newRuntimeWiring().type("Query",
				typeWriting -> typeWriting.dataFetcher("getAllPerson", fetcher1).dataFetcher("findPerson", fetcher2))
				.build();
	}

	@PostMapping("/getAll")
	public ResponseEntity<Object> getAll(@RequestBody String query) {
		ExecutionResult result = graphql.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/getPersonByEmail")
	public ResponseEntity<Object> getPersonByEmail(@RequestBody String query) {
		ExecutionResult result = graphql.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}



	@PostMapping("/addPerson")
	public String addPerson(@RequestBody List<Person> persons) {
		System.out.println("Inside addPerson Method");

		personRepository.saveAll(persons);

		return "Records Intserted "+persons.size();
	}

	@GetMapping("/findAllPerson")
	public List<Person> getPerson() {

		return (List<Person>)personRepository.findAll();
	}
}
