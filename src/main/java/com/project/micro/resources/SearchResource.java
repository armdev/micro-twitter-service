package com.project.micro.resources;

import com.project.micro.dao.TwitterSearchDAO;
import com.project.micro.model.TweetModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.ToString;

@Path("/v1/search")
@Api("/v1/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ToString
public class SearchResource {

    private final TwitterSearchDAO twitterSearchDAO;

    public SearchResource(TwitterSearchDAO twitterSearchDAO) {
        this.twitterSearchDAO = twitterSearchDAO;
    }

    @GET
    @Path("/{key}")
    @ApiOperation("Twitter search")
    @ApiResponses(value = {
        @ApiResponse(code = 202, message = "Found twitter search result")
        ,        
        @ApiResponse(code = 404, message = "Could not find any tweet")
    })
    public Response findTweets(@PathParam("key") @NotNull final String key) {
        Optional<List<TweetModel>> resultList = twitterSearchDAO.doSearch(key);
        if (!resultList.isPresent()) {
            return Response.serverError().entity(new ArrayList<>()).status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
        }
        return Response.ok(resultList.get()).status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }

}
