package ru.vokazak.ExchangeRatesComparator.pojo;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class GifObject {

    //By default, this is almost always GIF. ex:"gif"
    private String type;

    //Every GIF has unique ID ex:"YsTs5ltWtEhnq"
    private String id;

    //The unique slug used in GIF's URL	ex:"confused-flying-YsTs5ltWtEhnq"
    private String slug;

    //The unique URL for GIF ex:"http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq"
    private String url;

    //The unique bit.ly URL for GIF	ex:"http://gph.is/1gsWDcL"
    private String bitly_url;

    //A URL used for embedding GIF	ex:"http://giphy.com/embed/YsTs5ltWtEhnq"
    private String embed_url;

    //The username GIF is attached to, if applicable "JoeCool4000"
    private String username;

    //The page on which this GIF was found ex:"http://www.reddit.com/r/reactiongifs/comments/1xpyaa/superman_goes_to_hollywood/"
    private String source;

    //The MPAA-style rating for this content. Examples include Y, G, PG, PG-13 and R "g"
    private String rating;

    //Currently unused
    private String content_Url;

    //An object containing data about the user associated with this GIF, if applicable.
    private User user;

    //The top level domain of the source URL ex:"cheezburger.com"
    private String source_tld;

    //The URL of the webpage on which this GIF was found. ex:"http://cheezburger.com/5282328320"
    private String source_post_url;

    // The date on which this GIF was last updated.	ex:"2013-08-01 12:41:48"
    private String update_datetime;

    //The date this GIF was added to the GIPHY database. ex:"2013-08-01 12:41:48"
    private String create_datetime;

    //The creation or upload date from this GIF's source. ex:"2013-08-01 12:41:48"
    private String import_datetime;

    //The date on which this gif was marked trending, if applicable. ex:"2013-08-01 12:41:48"
    private String trending_datetime;

    //An object containing data for various available formats and sizes of this GIF.
    //private Images images;

    //The title that appears on giphy.com for this GIF.	ex:"Happy Dancing GIF"
    private String title;

}
