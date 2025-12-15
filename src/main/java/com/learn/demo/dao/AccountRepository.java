package com.learn.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.demo.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
