package pl.edu.agh.kis.soa.resources.service;

import org.hibernate.Hibernate;
import pl.edu.agh.kis.soa.resources.model.Student;
import pl.edu.agh.kis.soa.resources.dao.StudentDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by maciek on .06..
 */
public class StudentService {
    private static StudentDao StudentDao;

    public StudentService() {
        StudentDao = new StudentDao();
    }

    public int persist(Student entity) {
        StudentDao.openCurrentSessionwithTransaction();
        StudentDao.persist(entity);
        int id = entity.getId();
        StudentDao.closeCurrentSessionwithTransaction();
        return id;
    }

    public void update(Student entity) {
        StudentDao.openCurrentSessionwithTransaction();
        StudentDao.update(entity);
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public Student findById(int id) {
        StudentDao.openCurrentSession();
        Student student = StudentDao.findById(id);
        Hibernate.initialize(student.getSubjects());
        StudentDao.closeCurrentSession();
        return student;

    }

    public void delete(int id) {
        StudentDao.openCurrentSessionwithTransaction();
        Student student = StudentDao.findById(id);
        StudentDao.delete(student);
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public List<Student> findAll() {
        StudentDao.openCurrentSession();
        List<Student> students = StudentDao.findAll();
        for(Student student : students) {
            Hibernate.initialize(student.getSubjects());
        }
        StudentDao.closeCurrentSession();
        return students;
    }

    public void deleteAll() {
        StudentDao.openCurrentSessionwithTransaction();
        StudentDao.deleteAll();
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public StudentDao StudentDao() {
        return StudentDao;
    }
}
