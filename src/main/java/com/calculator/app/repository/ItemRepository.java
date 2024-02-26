package com.calculator.app.repository;

import com.calculator.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT SUM(i.totalBilledAmount) FROM Item i WHERE i.date = :requestedDate")
    Double getTotalBillAmountForRequestedDate(@Param("requestedDate") LocalDate requestedDate);

    @Query("SELECT i FROM Item i WHERE i.paymentStatus = 'Due'")
    List<Item> getDueTransactionsData();

}
