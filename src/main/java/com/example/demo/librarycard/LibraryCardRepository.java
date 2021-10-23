package com.example.demo.librarycard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {

    @Query("SELECT lib_card FROM Library_card lib_card WHERE lib_card.card_number = ?1 ")
    Optional<LibraryCard >findLibraryCardByCardNumber(String card_number);
}
