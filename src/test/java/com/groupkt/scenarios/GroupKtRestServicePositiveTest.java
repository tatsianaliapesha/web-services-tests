package com.groupkt.scenarios;

import com.fasterxml.jackson.databind.JsonNode;
import com.groupkt.apachehttpclient.ApacheHttpClientRestService;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GroupKtRestServicePositiveTest {

    @Test
    @Parameters({"stateCode", "capital"})
    public void getStateEntityByCodePositiveTest(String stateCode, String capital) throws Exception {
        ApacheHttpClientRestService service = new ApacheHttpClientRestService();
        ArrayList<Object> responseArray = service.sendApacheGetRequest(stateCode);
        Assert.assertEquals(200, responseArray.get(0), "Incorrect response code from get request. Expected status 200, but actual " + responseArray.get(0));
        Assert.assertEquals(capital, (((JsonNode) responseArray.get(2)).get("capital").textValue()), "Incorrect capital of selected state!");
    }

}
