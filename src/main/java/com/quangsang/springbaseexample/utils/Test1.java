package com.quangsang.springbaseexample.utils;

import com.sun.jersey.core.header.ContentDisposition;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;

public class Test1 {

    @GET
    @Path("/{fileName}")
    @Produces({"application/octet-stream"})
    public static javax.ws.rs.core.Response downloadVideo(@PathParam("fileName") String fileName) throws Exception {
        File videoDir = new File("C:\\Users\\quang\\Desktop\\listen video 5 miniutes\\9convert.com - Money and lifestyle  6 Minute English_360p.mp4");
        if (!videoDir.exists()) {
            videoDir.mkdirs();
        }

        File videoFile = new File(videoDir, URLDecoder.decode(fileName, "UTF8"));
        if (!videoFile.exists()) {
            return javax.ws.rs.core.Response.status(404).build();
        } else {
            ContentDisposition contentDisposition = ContentDisposition.type("attachment").fileName(videoFile.getName()).creationDate(new Date()).build();
            return javax.ws.rs.core.Response.ok( (StreamingOutput) output -> {
                try {
                    InputStream input = new FileInputStream( videoFile );
                    IOUtils.copy(input, output);
                    output.flush();
                } catch ( Exception e ) { e.printStackTrace(); }
            } ).header( "Content-Disposition", contentDisposition ).build();

        }
    }


    public static void main(String[] args) throws Exception {
        String fileName= "sang.png";
        downloadVideo(fileName);
    }
}
