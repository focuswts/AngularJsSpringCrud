package com.angularjs.crud.angularjsSpringCrud.repository;

import org.springframework.data.repository.CrudRepository;

import com.angularjs.crud.angularjsSpringCrud.entity.User;

public interface UserRepository  extends CrudRepository<User, Long>{

}
