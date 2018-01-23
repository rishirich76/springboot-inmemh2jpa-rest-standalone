/**
 * 
 */
package com.jrdtest.resttest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrdtest.resttest.entity.Transaction;


/**
 * @author Rishi
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
