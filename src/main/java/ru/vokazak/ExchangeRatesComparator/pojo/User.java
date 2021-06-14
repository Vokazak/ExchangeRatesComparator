package ru.vokazak.ExchangeRatesComparator.pojo;

import lombok.Data;

@Data
public class User {

    //The URL for this user's avatar image.	ex:"https://media1.giphy.com/avatars/election2016/XwYrZi5H87o6.gif"
    private String avatar_url;

    //The URL for the banner image that appears atop this user's profile page. ex:"https://media4.giphy.com/avatars/cheezburger/XkuejOhoGLE6.jpg"
    private String banner_url;

    // The URL for this user's GIPHY profile. ex:"https://giphy.com/cheezburger/"
    private String profile_url;

    //The username associated with this user. ex:"joecool4000"
    private String username;

    //The display name associated with this user (contains formatting the base username might not).
    private String display_name;

}
