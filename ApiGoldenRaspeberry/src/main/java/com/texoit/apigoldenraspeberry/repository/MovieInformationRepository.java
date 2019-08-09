package com.texoit.apigoldenraspeberry.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.texoit.apigoldenraspeberry.entity.MovieInformation;
import com.texoit.apigoldenraspeberry.entity.ResultApiPOJO;

@Repository
public interface MovieInformationRepository extends JpaRepository<MovieInformation, Long> {
	
	@Query(value = " SELECT new com.texoit.apigoldenraspeberry.entity.ResultApiPOJO(producer, (max(year) - min(year)) as yearInterval, min(year) as minYear, max(year) as maxYear, winner) "
			     + "   FROM MovieInformation "
			     + "  WHERE winner = true "
			     + "  GROUP BY producer, winner ")
	public Collection<ResultApiPOJO> getProducers();
}
