package com.wiselytop.elasticsearchfuzzyquery.repository;

import com.wiselytop.elasticsearchfuzzyquery.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by lijian on 2018/12/19
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
