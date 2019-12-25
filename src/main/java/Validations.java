import org.json.*;
import java.util.*;
import utils.ResponseCode;


public class Validations{

	public void EmptyCollections(Response response){
		Assert.assertTrue((response.jsonPath().get("collections")==null), "Response collections is not as expected i.e. not empty");
		
	}

	public void ProperCollections(Response response){
		 List<Map> collections = response.jsonPath().get("collections");
		 long nullCount = collections.stream().filter(collection -> collection.get("collection_id") == null || collection.get("url") == null || collection.get(res_count)== null).count();
		 Assert.assertTrue(nullcount==0, "The given reponse has invalid collections");
	}

	public void GoodResponse(Response response){//checking HTTP Status = 200
		Assert.assertTrue(response.getStatusCode()== OK, "Response status is not as expected i.e. not 200");
		Assert.assertTrue(isValidJSON(response.asString()), "Response hasn't given a valid json");
	}
	
	
	public void  BadResponse(Response response){//checking HTTP Status = 400
		
		Assert.assertTrue(response.getStatusCode()== BAD_REQUEST, "Response status is not as expected i.e. not 400");
	}
	
	
	
	public boolean isValidJSON(String test){
	
		try {
        		new JSONObject(test);
    		}
    		catch (JSONException ex) {
                	try {
            			new JSONArray(test);
        		}
        		catch (JSONException ex1) {
            			return false;
        		}
    		}
    		return true;
	}
	
	
} 


