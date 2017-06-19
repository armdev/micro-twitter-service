package com.project.micro.application;

import com.project.micro.dao.TwitterSearchDAO;
import com.project.micro.resources.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApplication extends Application<MainConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApplication.class);

    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
          bootstrap.addBundle(new SwaggerBundle<MainConfiguration>() {
            @Override
            public SwaggerBundleConfiguration getSwaggerBundleConfiguration(MainConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setHttpConnectionTimeout(15 * 1000);
        cb.setHttpReadTimeout(60 * 1000);
        cb.setUser(configuration.getUser());
        cb.setPassword(configuration.getPass());
        cb.setOAuthAccessToken(configuration.getAccessToken());
        cb.setOAuthAccessTokenSecret(configuration.getAccessTokenSecret());
        cb.setOAuthConsumerSecret(configuration.getConsumerSecret());
        cb.setOAuthConsumerKey(configuration.getConsumerKey());
        Configuration conf = cb.build();
        Twitter twitter = new TwitterFactory(conf).getInstance();
        TwitterSearchDAO twitterSearchDAO = new TwitterSearchDAO(twitter);

        environment.jersey().register(new SearchResource(twitterSearchDAO));

    }
}
