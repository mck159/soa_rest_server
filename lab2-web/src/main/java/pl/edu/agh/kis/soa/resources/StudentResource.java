package pl.edu.agh.kis.soa.resources;

import org.hibernate.StaleStateException;
import pl.edu.agh.kis.soa.resources.model.Student;
import pl.edu.agh.kis.soa.resources.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Klasa wystawiająca interfejs REST.
 *
 * @author teacher
 */
@Path("rest")
@Stateless
public class StudentResource {

    private static final Logger logger = Logger.getLogger("StudentResource");
    private static final StudentService studentService = new StudentService();

    public StudentResource() {
    }

    @GET
    @Path("students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> readStudent() {
        return studentService.findAll();
    }

    @GET
    @Path("student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStudent(@PathParam("id") int id) {
        try {
            Student student = studentService.findById(id);
            return Response.status(Response.Status.OK).entity(student).build();
        } catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("student")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        int id = studentService.persist(student);
        String result = String.format("Location: student/%d", id);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("student/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Student student) {
        student.setId(id);
        try {
            studentService.update(student);
        } catch(StaleStateException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String result = String.format("Location: student/%s", id);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("student/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        try {
            studentService.delete(id);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
