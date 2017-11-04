package techexe.expedia.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * Location contains details related to  locations added by user to their wishlist.
 * It maps to corresponding DynamoDB table
 */
@lombok.Data
@DynamoDBDocument
@DynamoDBTable(tableName = "Location")
public class Location {
    @DynamoDBAttribute(attributeName = "locationId")
    private String locationId;
    @DynamoDBAttribute(attributeName = "locationName")
    private String locationName;
    @DynamoDBAttribute(attributeName = "description")
    private String description;


    @DynamoDBHashKey(attributeName = "latitude")
    private double latitude;
    @DynamoDBRangeKey(attributeName = "longitude")
    private double longitude;
    @DynamoDBAttribute(attributeName = "noOfVotes")
    private Integer noOfVotes;
    @DynamoDBAttribute(attributeName = "country")
    private String country;
    @DynamoDBAttribute(attributeName = "isPopular")
    private boolean isPopular;
    @DynamoDBAttribute(attributeName = "votedBy")
    private String votedBy;

}
