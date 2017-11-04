package techexe.expedia.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 *  User Account details.
 *  it maps to corresponding DynamoDB table
 */
@lombok.Data
@DynamoDBTable(tableName="User")
public class UserDetails {

    @DynamoDBHashKey(attributeName="id")
    private String id;
    @DynamoDBAttribute(attributeName="name")
    private String name;
    @DynamoDBAttribute(attributeName="gender")
    private String gender;
    @DynamoDBAttribute(attributeName="password")
    private String password;
    @DynamoDBAttribute(attributeName="emailId")
    private String emailId;
    @DynamoDBAttribute(attributeName="isActive")
    private boolean isActive;
    @DynamoDBAttribute(attributeName="hasVoted")
    private boolean isVoted;

}
