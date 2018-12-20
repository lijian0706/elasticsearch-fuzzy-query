package com.wiselytop.elasticsearchfuzzyquery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by lijian on 2018/12/19
 */
@Document(indexName = "person", type = "person", shards = 3, replicas = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private String phoneNum;
    private String name;
    private String gender;
    private Integer age;
}
