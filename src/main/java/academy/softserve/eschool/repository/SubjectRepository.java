package academy.softserve.eschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import academy.softserve.eschool.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	/**
     * Finds subjects that taught by a teacher with the specified id.
     * @param idTeacher Id of teacher
     * @return List of Subject objects
     */
	@Query(value = "SELECT DISTINCT subject.id, subject.name, subject.description FROM class_teacher_subject_link \n" +
			"LEFT JOIN subject ON class_teacher_subject_link.subject_id = subject.id \n" +
            "WHERE teacher_id = :idTeacher", nativeQuery=true)
    List<Subject> findSubjectsByTeacher(@Param("idTeacher") int idTeacher);
    
	/**
     * Finds subjects that study in classes with the specified id.
     * @param classId Id of class
     * @return List of Subject objects
     */
    @Query(value = "SELECT DISTINCT subject.id, subject.name, subject.description FROM class_teacher_subject_link \n" +
    		"LEFT JOIN subject ON class_teacher_subject_link.subject_id = subject.id \n" +
            "WHERE clazz_id = :classId", nativeQuery=true)
	List<Subject> findSubjectsByClass(@Param(value="classId") Integer classId);

    /**
     * Edit some subject
     *
     * @param id Id of subject
     * @param name Name of subject
     * @param description Description of subject
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE subject SET name = :subjectName, description = :subjectDescription WHERE id = :id", nativeQuery=true)
    void editSubject(@Param("id") int id, @Param("subjectName") String name, @Param("subjectDescription") String description);
}
