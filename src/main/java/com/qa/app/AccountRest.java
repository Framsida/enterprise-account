package com.qa.app;


import com.qa.app.service.business.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
public class AccountRest {

    @Inject
    AccountService service;

    @GET
    @Path("index")
    public String getIndex() {
        return "Index";
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/get/{id}")
    public String getAccount(@PathParam("id") Long id){
        return service.getAccount(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public String getAllAccount() {
        return service.getAllAccounts();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/delete/{id}")
    public String deleteAccount(@PathParam("id") Long id) {
        return service.deleteAccount(id);
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addAccount(String body) {
        return service.createAccount(body);
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String updateAccount(@PathParam("id") Long id, String body) {
        return service.updateAccount(id, body);
    }

    public void setService(AccountService service) {
        this.service = service;
    }

}
