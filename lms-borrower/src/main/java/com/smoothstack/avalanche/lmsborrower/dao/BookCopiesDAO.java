package com.smoothstack.avalanche.lmsborrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.avalanche.lmsborrower.entity.BookCopies;
import com.smoothstack.avalanche.lmsborrower.entity.BookCopiesId;


@Repository
public interface BookCopiesDAO extends JpaRepository<BookCopies , BookCopiesId>{

}
