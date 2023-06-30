package com.tezine.appquarkus.controllers;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.tezine.appquarkus.beans.Fruit;
import com.tezine.appquarkus.codes.Logger;
import com.tezine.appquarkus.services.FruitsService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@RequestScoped
@Path("/v1/fruits")
@Tag(name = "FruitsController", description = "Fruits operations.")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitsController {

    @Inject FruitsService fruitService;

    @GET
    @Path("/get-fruits")
    @Operation(summary = "Returns the list of fruits")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Fruit.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response getFruits() {
        try {
            var list=fruitService.getFruits(); 
            return Response.ok(list).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/get-fruit/{id}")
    @Operation(summary = "Returns a fruit by its id")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Fruit.class)))
    @APIResponse(responseCode = "404",description = "Not Found")
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response getFruit(@PathParam("id") String id) {
        try {
            var fruit=fruitService.getFruit(id); 
            if(fruit==null)return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(fruit).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/add-fruit")
    @Operation(summary = "Adds a fruit to the list")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.STRING)))
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response addFruit(Fruit fruit) {
        try {
             var fruitID=fruitService.addFruit(fruit);
             if(fruitID==null)return Response.status(Status.BAD_REQUEST).build();
            return Response.ok(fruitID).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/update-fruit")
    @Operation(summary = "Updates a fruit")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404",description = "Not Found")
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response updateFruit(Fruit fruit) {
        try {
             var fruitID=fruitService.updateFruit(fruit);
            return Response.ok(fruitID).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete-fruit")
    @Operation(summary = "Deletes a fruit from the list")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404",description = "Not Found")
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response deleteFruit(Fruit fruit) {
        try {
            var ok=fruitService.deleteFruit(fruit);
            if(ok)return Response.ok().build();
            else return Response.status(Status.NOT_FOUND).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete-fruit-by-id/{id}")
    @Operation(summary = "Deletes a fruit by id from the list")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404",description = "Not Found")
    @APIResponse(responseCode = "500",description = "Internal Server Error")
    public Response deleteFruitByID(@PathParam("id") String id) {
        try {
            var ok=fruitService.deleteFruitByID(id);
            if(ok)return Response.ok().build();
            else return Response.status(Status.NOT_FOUND).build();
        } catch (Exception e) {
            Logger.logError(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
