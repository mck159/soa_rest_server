package pl.edu.agh.kis.soa.resources.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.kis.soa.resources.model.Student;
;
import java.util.List;

/**
 * Created by maciek on 16.06.15.
 */
public class StudentDao implements StudentDaoInterface<Student, Integer> {
    private Session currentSession;

    private Transaction currentTransaction;

    public StudentDao() {

    }


    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Student entity) {
        getCurrentSession().save(entity);
    }

    public void update(Student entity) {
        getCurrentSession().update(entity);
    }

    public Student findById(Integer id) {
        Student Student = (Student) getCurrentSession().get(Student.class, id);
        return Student;
    }

    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        List<Student> Students = (List<Student>) getCurrentSession().createQuery("from Student").list();
        return Students;
    }

    public void deleteAll() {
        List<Student> entityList = findAll();
        for (Student entity : entityList) {
            delete(entity);
        }
    }


}
