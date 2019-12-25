import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import utils.ApiUtils;
import com.company.project.validations.CollectionsValidations;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ApiUtils;
import utils.CollectionApiConfig;
import utils.ResponseCode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




@Listeners(/Listener.class)  
public class TestCaseReader {
    
    public static void main (String args[]) {
 
    try {
        FileInputStream fis = new FileInputStream(/testcases.xlsx);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheet("testdata");
        
        for(int count = 1;count<=sheet.getLastRowNum();count++){
            HSSFRow row = sheet.getRow(count);
            System.out.println("Running test case " + row.getCell(0).toString());
            // Run the test for the current row test data. Passing url & expected status code as arguments.
            runTest(makeurl(row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString()), row.getcell(5));
            
            System.out.println("Completed Running the test case " + row.getCell(0).toString());
        }
        fis.close();
    } catch (IOException e) {
        System.out.println("Test data file not found");
    }   
}


	@Test
	public static void runTest(String url, int expectedResponseCode) {
		 
		Validations validate = new Validations();
		 
		if(expectedResponseCode == 200){
			Response response= ApiUtils.getApiResponse(OK,uri);
			
			validate.GoodResponse(response)//checks the HTTP Status
			validate.ProperCollections(response);	
			
		}
		
		else{
			Response response= ApiUtils.getApiResponse(BAD_REQUEST,uri);
			validate.BadResponse(response)//checks the  HTTP Status
		}
					
	}
	
	
	
	
	//returns the url corresponding to the given parameters.
	public static String makeurl(String cityId,  String latitude, String longitude, String count) {
	
		String baseurl = "https://developers.zomato.com/api/v2.1/collections";
	
		if(!cityId.equals("") && latitude.equals("") && longitude.equals("") && count.equals("")) {
			return baseurl+"?city_id="+cityId);
		
		}
		
		if(cityId.equals("") && !latitude.equals("") && !longitude.equals("") && count.equals("")) {
			
			return baseurl+"?lat="+latitude+"&lon="+longitude);
		
		}
		
		if(!cityId.equals("") && latitude.equals("") && longitude.equals("") && !count.equals("")) {
		
			//"https://developers.zomato.com/api/v2.1/collections?city_id="+cityId+"&count="+count
			
			return baseurl+"?city_id="+cityId+"&count="+count;
		
		}
	}
}

