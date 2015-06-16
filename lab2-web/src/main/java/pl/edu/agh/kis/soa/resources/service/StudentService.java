package pl.edu.agh.kis.soa.resources.service;

import pl.edu.agh.kis.soa.resources.model.Student;
import pl.edu.agh.kis.soa.resources.dao.StudentDao;

import java.util.List;

/**
 * Created by maciek on .06..
 */
public class StudentService {
    private static StudentDao StudentDao;

    public StudentService() {
        StudentDao = new StudentDao();
    }

    public void persist(Student entity) {
        StudentDao.openCurrentSessionwithTransaction();
        StudentDao.persist(entity);
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public void update(Student entity) {
        StudentDao.openCurrentSessionwithTransaction();
        StudentDao.update(entity);
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public Student findById(int id) {
        StudentDao.openCurrentSession();
        Student Student = StudentDao.findById(id);
        StudentDao.closeCurrentSession();
        return Student;

    }

    public void delete(int id) {
        StudentDao.openCurrentSessionwithTransaction();
        Student Student = StudentDao.findById(id);
        StudentDao.delete(Student);
        StudentDao.closeCurrentSessionwithTransaction();
    }

    public List<Student> findAll() {
        StudentDao.openCurrentSession();
        List<Student> Students = StudentDao.findAll();
        StudentDao.closeCurrentSession();
        return Students;
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
