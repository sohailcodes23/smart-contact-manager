package com.example.smartContactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "select * from customer where user_id = ?1")
    Customer findByIamObject(Long IamObjectId);

}


