package com.project.micro.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Getter;

public class MainConfiguration extends Configuration {

    @JsonProperty("twitter.user")
    @Getter
    private String user;

    @JsonProperty("twitter.pass")
    @Getter
    private String pass;

    @JsonProperty("twitter.accesstoken")
    @Getter
    private String accessToken;

    @JsonProperty("twitter.accesstokensecret")
    @Getter
    private String accessTokenSecret;

    @JsonProperty("twitter.consumersecret")
    @Getter
    private String consumerSecret;

    @JsonProperty("twitter.consumerkey")
    @Getter
    private String consumerKey;

    @JsonProperty("swagger")
    @Getter
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

}
