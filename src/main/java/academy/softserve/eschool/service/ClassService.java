package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.model.Clazz;

import java.util.List;

public interface ClassService {
    List<ClassDTO> getAllClasses();
    ClassDTO findClassById(int id);
    ClassDTO saveClass(ClassDTO classDTO);
    ClassDTO updateClass(int id, ClassDTO classDTO);
    List<ClassDTO> addNewYearClasses();
    void updateClassStatusById(List<NYTransitionDTO> transitionDTOS, boolean status);
}
