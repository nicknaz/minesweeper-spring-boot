package com.minesweaper.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minesweaper.demo.entity.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
