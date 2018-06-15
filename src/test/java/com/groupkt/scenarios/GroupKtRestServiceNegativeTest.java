package com.groupkt.scenarios;

import com.groupkt.apachehttpclient.ApacheHttpClientRestService;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GroupKtRestServiceNegativeTest {

    @Test
    @Parameters({"stateCode"})
    public void getStateEntityByCodeNegativeTest(String stateCode) throws Exception {
        ApacheHttpClientRestService service = new ApacheHttpClientRestService();
        ArrayList<Object> responseArray = service.sendApacheGetRequest(stateCode);
        Assert.assertEquals(200, responseArray.get(0), "Incorrect response code from get request. Expected status 200, but actual " + responseArray.get(0));
        Assert.assertEquals("[\"No matching state found for requested code [USA->" + stateCode + "].\"]", responseArray.get(1), "Incorrect response message from the request.");
    }
}
