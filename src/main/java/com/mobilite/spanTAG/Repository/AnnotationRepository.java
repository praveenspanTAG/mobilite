package com.mobilite.spanTAG.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilite.spanTAG.Entity.Annotation;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
	
}
