# ElasticSearch相似度查询
在使用`ElasticSearch`时，有时会遇到这样的类似的业务场景:检索与给定手机号相似的人，例如给定手机号18000000000，系统需检索出诸如手机号为18000000001，18000000002，18100000001等等相似的人的信息。下面我们使用`ElasticSearch`的`Fuzzy Query`来实现手机号的相似度检索。本例使用的`spring boot`版本为`2.0.6.RELEASE`,`spring-boot-starter-data-jest`版本为`3.1.2.RELEASE`。

## 1.前期准备工作
- `Entity`:

```
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

```
- `Repository`:

```
public interface PersonRepository extends ElasticsearchRepository<Person, Long> {

}
```
## 2.构造`Fuzzy Query`查询条件

```
QueryBuilder queryCondition = QueryBuilders.fuzzyQuery("phoneNum", fuzzyPhoneNum).fuzziness(Fuzziness.AUTO);
personRepository.search(queryCondition);
```
说明:
- `QueryBuilders.fuzzyQuery('字段名', 字段值)`
- `fuzziness()`方法以`编辑距离`为入参，取值有四种：0，1，2，'AUTO'

源码地址：https://github.com/a479159321/elasticsearch-fuzzy-query

