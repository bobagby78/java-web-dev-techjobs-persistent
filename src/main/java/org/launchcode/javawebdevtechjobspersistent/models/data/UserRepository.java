package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);  //this is called a Query Method. check out the Spring documentation. https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
}
