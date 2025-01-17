package com.mobilite.spanTAG.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilite.spanTAG.Entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
	
}