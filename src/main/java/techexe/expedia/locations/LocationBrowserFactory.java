package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;

public class LocationBrowserFactory {

    public ILocationBrowser locationBrowser;


    public ILocationBrowser getLocationBrowser(String type) {
        switch (type){
            case "Votes":{
                locationBrowser = new LocationBrowserByVotes();
                break;
            }
            case "Distance":{

            }
            case "Country":{

            }
            default:{

            }


        }
        return locationBrowser;
    }
}
