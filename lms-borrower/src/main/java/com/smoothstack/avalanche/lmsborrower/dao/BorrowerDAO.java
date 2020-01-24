package com.smoothstack.avalanche.lmsborrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.avalanche.lmsborrower.entity.Borrower;


@Repository
public interface BorrowerDAO extends JpaRepository<Borrower ,Long>{

}
