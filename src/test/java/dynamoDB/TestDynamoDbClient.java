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

public class TestDynamoDbClient {

    @Test
    public void testAmazonDynamoDBClientCreation() throws IOException {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        Assert.assertNotNull(mapper, "DynamoDB Mapper is null");
    }

    @Test
    public void testUserDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        for (int i = 0; i < 10; i++) {
            mapper.save(TestUtils.getUsers());
        }
    }

    @Test
    public void testLocationDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        mapper.save(TestUtils.getLocation("US",7,7.1450674,9.450674));

    }

    @Test
    public void testWishlistDetailsPersistance() {
        DynamoDBMapper mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
        for (int i = 0; i < 10; i++) {
            mapper.save(TestUtils.getWishList());
        }
    }


    @Test
    public void getUserById() {
        DynamoDBWrapper wrp = new DynamoDBWrapper();
        UserDetails user = wrp.getMapper().load(UserDetails.class, "testing");
        System.out.print("sdsds" + user.getName());

    }


    // @Test
    public void loadLatLngData() throws IOException {
        DynamoDBWrapper wrp = new DynamoDBWrapper();
        List<GlobalLatLngDetails> gl = CSVFileReader.readLatLongDetailsFrom("src/main/resources/zip_city_state.csv");
        for (GlobalLatLngDetails gll : gl) {
            wrp.getMapper().save(gll);
        }

    }
}
