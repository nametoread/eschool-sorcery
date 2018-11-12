package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.model.*;
import academy.softserve.eschool.repository.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * This class implements the interface {@link ClassTeacherSubjectService}
 * and contains a method that saves the connection between a teacher, a subject and a class
 * in the class_teacher_subject_link table.
 *
 * @author Mariana Vorotniak
 */
@Service
public class ClassTeacherSubjectServiceImpl implements ClassTeacherSubjectService {

    @Autowired
    private ClassTeacherSubjectLinkRepository classTeacherSubjectRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    /**
     * This method saves the class-teacher-subject connection into the class_teacher_subject_link table
     * @param teacherJournalDTO {@link TeacherJournalDTO} object, fields of which need to be inserted into the table in DB
     * @param isActive          indicates if is this connection is up-to-date
     */
    @Override
    public void saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive) {
        ClassTeacherSubjectLink classTeacherSubject = new ClassTeacherSubjectLink();

        int classId = teacherJournalDTO.getClassId();
        int teacherId = teacherJournalDTO.getTeacherId();
        int subjectId = teacherJournalDTO.getSubjectId();

        Clazz clazz = classRepository.findById(classId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        Subject subject = subjectRepository.findById(subjectId).get();

        classTeacherSubjectRepository.save(classTeacherSubject.builder()
                .clazz(clazz)
                .clazz_id(classId)
                .teacher(teacher)
                .teacher_id(teacherId)
                .subject(subject)
                .subject_id(subjectId)
                .isActive(isActive).build());
    }

}
