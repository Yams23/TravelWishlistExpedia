package dynamoDB;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.model.*;
import techexe.expedia.utils.CSVFileReader;
import techexe.expedia.utils.Utils;
import utils.TestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Class to verify DynamoDBClient initialization and persistance operations
 */
public class TestDynamoDbClient {

    @Test(description = "Verify DynamoDB Mapper client creation")
    public void testAmazonDynamoDBClientCreation() throws IOException {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        Assert.assertNotNull(mapper, "DynamoDB Mapper is null");
    }

    @Test(description = "Verify if user details can be persisted in DynamoDB")
    public void testUserDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        mapper.save(TestUtils.getUsers());

    }

    @Test(description = "Verify if location details can be persisted in DynamoDB")
    public void testLocationDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        mapper.save(TestUtils.getLocation("US", 7, 7.1450674, 9.450674));

    }

    @Test(description = "verify if wish list details can be persisted in DynamoDB")
    public void testWishlistDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        for (int i = 0; i < 10; i++) {
            mapper.save(TestUtils.getWishList());
        }
    }


    @Test(description = "Verify if the user fetched from DyDB matches with expected user details")
    public void getUserById() {
        DynamoDBWrapper wrp = new DynamoDBWrapper();
        UserDetails user = wrp.getMapper().load(UserDetails.class, "testing");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "Yamini");

    }


    @Test(description = "Method to load data to GlobalLatLngDetails table", enabled = false)
    public void loadLatLngData() throws IOException {
        DynamoDBWrapper wrp = new DynamoDBWrapper();
        List<GlobalLatLngDetails> gl = CSVFileReader.readLatLongDetailsFrom("src/main/resources/zip_city_state.csv");
        for (GlobalLatLngDetails gll : gl) {
            wrp.getMapper().save(gll);
        }

    }
}
