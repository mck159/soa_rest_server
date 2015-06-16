package pl.edu.agh.kis.soa.resources;

import pl.edu.agh.kis.soa.resources.model.Student;
import pl.edu.agh.kis.soa.resources.service.StudentService;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.ejb.Stateless;
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
    @Path("student/{albumNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student readStudent(@PathParam("albumNo") String albumNo) {
        if (albumNo.equals("258433")) {
            Student s = new Student();
            s.setFirstName("Maciek");
            s.setLastName("Sroka");
            s.setAlbumNo("258433");
            s.setSubjects(Arrays.asList("SOA", "Bazy danych"));
            return s;
        }
        return null;
    }

    @POST
    @Path("student")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        studentService.persist(student);
        String result = String.format("Location: student/%s", student.getAlbumNo());
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("student/{albumNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student student) {
        String result = String.format("Location: student/%s", student.getAlbumNo());
            return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("student/{albumNo}")
    public Response deleteStudent(@PathParam("albumNo") String albumNo) {
        if(albumNo.equals("258433")) {
            return Response.status(Response.Status.OK).build();
        }
        return null;
    }
}
