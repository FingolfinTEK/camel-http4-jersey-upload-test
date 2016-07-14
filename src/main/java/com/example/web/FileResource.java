package com.example.web;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/restwb")
public class FileResource {
    @GET
    @Produces("application/json")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(
        @FormDataParam("username") String username,
        @FormDataParam("password") String password,
        @FormDataParam("upload") InputStream is) throws IOException {
        String output =
            "Hi " + username + " your password is " + password + "\n" + IOUtils.toString(is);
        System.out.println(output);
        return output;
    }
}