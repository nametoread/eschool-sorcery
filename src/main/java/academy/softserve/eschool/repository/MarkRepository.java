package academy.softserve.eschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.softserve.eschool.model.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
	
	@Query(value="select m from mark m left join lesson l on m.lesson_id = l.id"
			+ "where (:subjectId is null or m.subject_id = :subjectId)"
			+ "and (:classId is null or l.clazz_id = :classId)"
			+ "and (:studentId is null or m.student_id = :studentId)"
			+ "and (:startDate is null or l.date >= :startDate)"
			+ "and (:endDate is null or l.date <= :endDate)", nativeQuery=true)
	public List<Mark> findAll(@Param("subjectId") Integer subjectId, @Param("classId")Integer classId,
			@Param("studentId") Integer studentId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	
}
