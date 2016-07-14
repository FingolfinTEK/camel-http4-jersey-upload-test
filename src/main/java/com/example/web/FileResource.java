package com.example.web;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public String uploadFile(@FormDataParam("username") String username,
        @FormDataParam("password") String password, @FormDataParam("upload") InputStream is) {
        String output = "Hi " + username + " your password is " + password;
        output = output + IOUtils.LINE_SEPARATOR + IOUtils.LINE_SEPARATOR;
        output = output + "Output :" + IOUtils.LINE_SEPARATOR
            + "------------------------------------------------------------------------------"
            + IOUtils.LINE_SEPARATOR;
        try {
            output =
                output + IOUtils.toString(is) + IOUtils.LINE_SEPARATOR + IOUtils.LINE_SEPARATOR;
            output = output
                + 
                "==================================================================================================="
                + IOUtils.LINE_SEPARATOR + IOUtils.LINE_SEPARATOR;
            System.out.println("Output :" + output);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }
}