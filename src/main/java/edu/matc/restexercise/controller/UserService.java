package edu.matc.restexercise.controller;

import edu.matc.restexercise.entity.User;
import edu.matc.restexercise.entity.UserRole;
import edu.matc.restexercise.persistence.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/user")
public class UserService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public Response getMessage(@PathParam("userId") String userId) throws Exception{

        UserDao dao = new UserDao();
        User user = dao.getUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() throws Exception {

        UserDao dao = new UserDao();
        List<User> users = dao.getAllUsers();
        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
        return Response.status(200).entity(output).build();
    }
}
