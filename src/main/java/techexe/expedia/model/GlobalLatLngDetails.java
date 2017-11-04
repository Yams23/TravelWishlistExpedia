package techexe.expedia.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * This Class contains attributes corresponding GlobalLatLong Table.
 * This table contains static set of latitude and longitude details across different regions in the world
 * It maps to corresponding DynamoDB table
 */
@lombok.Data
@DynamoDBTable(tableName = "GlobalLatLong")
public class GlobalLatLngDetails {
    @DynamoDBAttribute(attributeName = "zipCode")
    private String zipCode;
    @DynamoDBHashKey(attributeName = "latitude")
    private double latitude;
    @DynamoDBRangeKey(attributeName = "longitude")
    private double longitude;
    @DynamoDBAttribute(attributeName = "city")
    private String city;
    @DynamoDBAttribute(attributeName = "state")
    private String state;
    @DynamoDBAttribute(attributeName = "country")
    private String country;
}
