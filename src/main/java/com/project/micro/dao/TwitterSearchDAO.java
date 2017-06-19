/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.micro.dao;

import com.project.micro.model.TweetModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author armdev
 */
public class TwitterSearchDAO {

    private final Twitter twitter;

    public TwitterSearchDAO(Twitter twitter) {
        this.twitter = twitter;
    }

    public Optional<List<TweetModel>> doSearch(String key) {
        List<TweetModel> resultList = new ArrayList<>();
        TweetModel tweetModel = new TweetModel();

        Query query = new Query(key);
        QueryResult result = null;

        try {
            query.setCount(10);
            result = twitter.search(query);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterSearchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result == null) {
            System.out.println("Did not find any result");
            return null;
        }
        List<Status> tweetsList = result.getTweets();
        if (!tweetsList.isEmpty()) {
            tweetsList.stream().map((status) -> {
                tweetModel.setCreatedAt(status.getCreatedAt() != null ? status.getCreatedAt() : null);
                return status;
            }).map((status) -> {
                tweetModel.setFavoriteCount(status.getFavoriteCount());
                return status;
            }).map((status) -> {
                tweetModel.setFavorited(status.isFavorited());
                return status;
            }).map((status) -> {
                tweetModel.setIsoLanguageCode(status.getLang());
                return status;
            }).map((status) -> {
                tweetModel.setLang(status.getLang());
                return status;
            }).map((status) -> {
                tweetModel.setReTweeted(status.isRetweeted());
                return status;
            }).map((status) -> {
                tweetModel.setRecordDate(new Date(System.currentTimeMillis()));
                tweetModel.setSource(status.getSource());
                return status;
            }).map((status) -> {
                tweetModel.setText(status.getText());
                return status;
            }).map((status) -> {
                tweetModel.setTweetedUserId(status.getUser().getId());
                return status;
            }).map((status) -> {
                tweetModel.setTweetId(status.getId());
                return status;
            }).map((status) -> {
                tweetModel.setFavorited(status.isFavorited());
                return status;
            }).map((status) -> {
                tweetModel.setRetweetedCount(status.getRetweetCount());
                return status;
            }).map((status) -> {
                tweetModel.setProfileImageURL(status.getUser().getProfileImageURL());
                return status;
            }).map((status) -> {
                tweetModel.setTweetUserName(status.getUser().getName() != null ? status.getUser().getName() : null);
                return status;
            }).map((status) -> {
                tweetModel.setTweetUserScreenName(status.getUser().getScreenName() != null ? status.getUser().getScreenName() : null);
                return status;
            }).map((status) -> {
                tweetModel.setTweetUserLocation(status.getUser().getLocation() != null ? status.getUser().getLocation() : null);
                return status;
            }).map((status) -> {
                tweetModel.setTweetUserDescription(status.getUser().getDescription() != null ? status.getUser().getDescription() : null);
                return status;
            }).forEachOrdered((_item) -> {
                resultList.add(tweetModel);
            });
        }

        return Optional.ofNullable(resultList);

    }

}
