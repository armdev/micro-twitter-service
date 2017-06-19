package com.project.micro.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author Armen Arzumanyan
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TweetModel implements Serializable {


    private static final long serialVersionUID = 1L;  

    private Long id;
    private Long searchId;
    private Long userId;
    private String searchName;
    private Long categoryId;
    private Date recordDate;    
    private Date createdAt;
    private Long tweetId;
    private Long tweetedUserId;//stored in tweet user entity
    private String text;
    private String source;
    private boolean favorited;
    private boolean reTweeted;
    private int favoriteCount;
    private String isoLanguageCode;
    private String lang;
    private int retweetedCount;
    private String tweetUserName;
    private String tweetUserScreenName;
    private String profileImageURL;
    private String tweetUserLocation;
    private String tweetUserDescription;    

 


}
