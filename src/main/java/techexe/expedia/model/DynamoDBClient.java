package techexe.expedia.model;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Singleton Class which initializes the Java client for AMAZON DynamoDB
 */
public class DynamoDBClient {
    // logger
    static Logger logger = Logger.getLogger(DynamoDBClient.class.getName());

    private static DynamoDBClient dynamoDBClient;
    private final String awsCredentialsFileLocation = "src/main/resources/AWSCredentials.properties";
    @lombok.Getter
    private DynamoDBMapper dynamoDBMapper;

    //Initiaze Amazon DynamoDB Mapper
    private DynamoDBClient() {
        logger.info("Initializing Amazon DynamoDBMapper");
        initializeAmazonDynamoDBMapper();
    }

    /**
     * Gets single instance of DynamoDBClient.
     *
     * @return dynamoDBClient
     */
    public static DynamoDBClient getInstance() {
        logger.info("Instantiating DynamoDBClient");
        if (dynamoDBClient == null) {
            dynamoDBClient = new DynamoDBClient();
        }
        return dynamoDBClient;
    }

    /**
     * Initialize Amazon Dynamo Db Mapper.
     */
    public void initializeAmazonDynamoDBMapper() {
        logger.info("Fetching the AWS credentials and initializing the AmazonDynamoDB Client");
        PropertiesCredentials credentials = null;
        try {
            // Get AWS Credentials from properties file
            credentials = new PropertiesCredentials(new File(awsCredentialsFileLocation));
            BasicAWSCredentials basicCredentials = new BasicAWSCredentials(credentials.getAWSAccessKeyId(), credentials.getAWSAccessKeyId());
            AmazonDynamoDB client = new AmazonDynamoDBClient(credentials).withRegion(Regions.US_EAST_2);
            dynamoDBMapper = new DynamoDBMapper(client);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"IO Exception thrown while reading AWS credentials from properties file");
            e.printStackTrace();
        }
    }
}
