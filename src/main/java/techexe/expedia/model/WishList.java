package techexe.expedia.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

/**
 * Wish list details respective to any user and It maps to corresponding DynamoDB table.
 */
@lombok.Data
@DynamoDBTable(tableName = "Wishlist")
public class WishList {
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "locations")
    private List<String> locationIds;
    @DynamoDBRangeKey(attributeName = "userId")
    private String userId;
    @DynamoDBHashKey(attributeName = "wishListId")
    private String wishListId;

    private String locationId;

    public String toString() {
        return "WishList Name - " + name + " belongs to User Id - " + userId;
    }


}
