package com.wiselytop.elasticsearchfuzzyquery;

import com.wiselytop.elasticsearchfuzzyquery.domain.Person;
import com.wiselytop.elasticsearchfuzzyquery.repository.PersonRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.wiselytop.elasticsearchfuzzyquery.repository")
@RestController
public class ElasticsearchFuzzyQueryApplication {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/persons")
	public Iterable<Person> fuzzyPersons(String fuzzyPhoneNum){
		QueryBuilder queryCondition = QueryBuilders.fuzzyQuery("phoneNum", fuzzyPhoneNum).fuzziness(Fuzziness.AUTO);
		return personRepository.search(queryCondition);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return clr -> {
			List<Person> persons = new ArrayList<>();
			persons.add(new Person("18000000000", "张三", "男", 23));
			persons.add(new Person("18000000001", "李四", "男", 24));
			persons.add(new Person("18000000002", "小明", "男", 23));
			persons.add(new Person("18100000000", "王五", "男", 28));
			personRepository.saveAll(persons);
			System.out.println();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchFuzzyQueryApplication.class, args);
	}

}

