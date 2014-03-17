package craigslist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;

public class Page implements Runnable {
    protected String url;
    protected String html;
    protected Features features;
    protected Boolean hasAllFeatures = false;

    public Page(String url) throws MalformedURLException, IOException {
        this.url = url;
    }
    
    public void run() {
        try {
            this.html = utils.http.get(this.url);

            populateFields();
        } catch (Exception exception) {
            System.out.print("Request failed:\n");
            System.out.print(exception);
        }
    }

    public Boolean hasAllFeatures() {
        return hasAllFeatures;
    }

    public Features getFeatures() {
        return features;
    }
    
    public String getUrl() {
        return url;
    }

    protected Boolean populateLatitude() {
        String pattern = "data-latitude=\"(\\d?\\d\\d\\.\\d+?)\"";
        if (!utils.patterns.hasAtLeastOneMatch(pattern, html)) {
            return false;
        }
        this.features.setLatitude(Double.parseDouble(utils.patterns.extract(
                pattern, html)));
        return true;
    }

    protected Boolean populateLongitude() {
        String pattern = "data-latitude=\"(\\d?\\d\\d\\.\\d+?)\"";
        if (!utils.patterns.hasAtLeastOneMatch(pattern, html)) {
            return false;
        }
        this.features.setLongitude(Double.parseDouble(utils.patterns.extract(
                pattern, html)));
        return true;
    }

    protected Boolean populatePrice() {
        String pattern = "(?:&#x0024;|$)(\\d{2,6})";
        if (!utils.patterns.hasAtLeastOneMatch(pattern, html)) {
            return false;
        }
        this.features.setPrice(Double.parseDouble(utils.patterns.extract(
                pattern, html)));
        return true;
    }

    protected Boolean populateNumberOfImages() {
        String pattern = "(<img src=\"http:\\/\\/images\\.craigslist\\.org)";
        Matcher matcher = utils.patterns.match(pattern, html);
        Integer numberOfImages = 0;
        while (matcher.find()) {
            ++numberOfImages;
        }

        if (numberOfImages > 0) {
            this.features.setNumberOfImages(numberOfImages);
            return true;
        } else {
            return false;
        }
    }

    protected Boolean populateLengthOfTitle() {
        String pattern = "<h2 class=\"postingtitle\">.*?<span class=\"star\"></span>(.*?)<\\/h2>";
        String title = utils.patterns.extract(pattern, html);
        this.features.setLengthOfTitle(title.length());
        return true;
    }

    protected void populateFields() {
        this.features = new Features();

        Boolean hasLatitude = populateLatitude();
        Boolean hasLongitude = populateLongitude();
        Boolean hasPrice = populatePrice();
        Boolean hasNumberOfImages = populateNumberOfImages();
        Boolean hasLengthOfTitle = populateLengthOfTitle();

        if (hasLatitude && hasLongitude && hasPrice && hasNumberOfImages
                && hasLengthOfTitle) {
            this.hasAllFeatures = true;
        }
    }
}
