package edu.matc.restexercise.controller;

import edu.matc.restexercise.entity.User;
import edu.matc.restexercise.entity.UserRole;
import edu.matc.restexercise.persistence.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Path("/user")
public class UserService {
    @GET
    @Produces({"text/plain,application/json,text/html"})
    @Path("/{userId}")
    public Response getMessage(@PathParam("userId") String userId) {

        UserDao dao = new UserDao();
        StringBuilder output =new StringBuilder();

        if (userId == null || userId.equals("")) {
            List<User> users = dao.getAllUsers();
            buildAllUsersOutput(output,users);
        } else {
            User user = dao.getUser(userId);
            List<User> users = new ArrayList<User>();
            users.add(user);
            buildAllUsersOutput(output,users);
        }

        return Response.status(200).entity(output.toString()).build();
    }

    private void buildAllUsersOutput(StringBuilder output,List<User> users) {

        int counter = 1;
        for (User current: users) {
            output.append("User #: " + counter);
            buildUserOutput(output,current);
            buildUserRoleOutput(output,current);
            counter+= 1;
            output.append("");
        }
    }

    private void buildUserOutput(StringBuilder output, User user) {
        output.append("User Name: " + user.getUserName());
        output.append("First Name: " + user.getFirstName());
        output.append("Last Name: " + user.getLastName());
        output.append("Email Address: " + user.getEmailAddress());
    }

    private void buildUserRoleOutput(StringBuilder output, User user) {
        Set<UserRole> roles = user.getUserRoles();
        int counter = 1;
        for (UserRole role: roles) {
            output.append("Role #: " + role.getRoleName());
            counter+= 1;
        }
    }



}
