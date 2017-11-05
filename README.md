# TravelWishlistExpedia


Module Level description

WishListManager

    The Wish list manager exposes APIs to perform CRUD operations on wish list. Given the user details, this module will be able to fetch the wish list created for that user or else create new wish list and add the location to the user’s wishlist. It also provides functionality to fetch list of wishlists for given user and to delete any wishlist. Wish list details is being persisted to DynamoDB table named ‘Wishlist’

Location Browser

    This module has been implemented using factory design pattern. The Location browser exposes api to fetch list of locations by given type – Votes, Popular Country or Distance from current user location. There are separate implementations for each browser type mentioned above. The module talks to DynamoDB data store and fetches the list of locations which were added by any user to their wishlist. Custom business logic has been implemented on top of this returned list of locations to get locations by respective types – votes/country or distance. Location details is being persisted to DynamoDB table named – ‘Location’

PlacePicker

    This module exposes APIs to pinpoint any location given latitude and longitude. It also provides interface to fetch the list of all the latitudes and longitudes available for locations across the world. Populated static set of location details – city, country, latitude, longitude and zip code into GlobalLatLngDetails table in DynamoDB which is being fetched by the APIs of PlacePicker to show various locations for users to pin-point & tag. These results can be used by user interface (CX) to show the locations across the world.

LocationVotingManager

    This module exposes API to vote for particular location given the latitude and longitude details and userId. It increments the count for particular location in the database and if the count is greater than five, it marks the place as popular.

Notifier
    The notification system fetches the list of locations from the DynamoDB store and queries the list of locations which are popular and corresponding user email Ids who have voted for that locations. Email is being sent to all those emails using Java Messaging system.

DynamoDB

    This data store handles the data model for this framework. It stores data related to User Details, WishList details, Location details and Global Latitude and Longitude details. DynamoDB Wrapper has been created on top of DynamoDB Java client which exposes APIs to fetch the above-mentioned data objects programmatically.

    The DynamoDB tables can also be managed via AWS console. The following snapshot shows list of tables created

