import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import craigslist.FeatureExtractor;
import craigslist.Features;
import craigslist.Frontpage;
import craigslist.Page;

public class ScanCraigslist {
    public static void main(String[] args) throws MalformedURLException, IOException {
        Frontpage frontpage = new Frontpage();
        List<Page> pages = frontpage.getPages();
        outputFeatureCsvToStdOut(FeatureExtractor.getFeatures(pages));
    }
    
    protected static String generateCsvFromFeatures(Map<String, Features> features) {
        StringBuilder csv = new StringBuilder();
        csv.append("url,price,latitude,longitude,number_of_images,length_of_title\n");
        
        for (String url : features.keySet()) {
            Features pageFeatures = features.get(url);
            
            csv.append(url); csv.append(",");
            csv.append(pageFeatures.getPrice()); csv.append(",");
            csv.append(pageFeatures.getLatitude()); csv.append(",");
            csv.append(pageFeatures.getLongitude()); csv.append(",");
            csv.append(pageFeatures.getNumberOfImages()); csv.append(",");
            csv.append(pageFeatures.getLengthOfTitle()); csv.append("\n");
        }
        
        System.out.print(csv);
        
        return csv.toString();
    }
    
    protected static void outputFeatureCsvToStdOut(Map<String, Features> features) {
        System.out.print(generateCsvFromFeatures(features));
    }
}