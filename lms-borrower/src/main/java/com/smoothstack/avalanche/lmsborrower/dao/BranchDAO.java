package com.smoothstack.avalanche.lmsborrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.avalanche.lmsborrower.entity.Branch;


@Repository
public interface BranchDAO extends JpaRepository<Branch ,Long>{

}
