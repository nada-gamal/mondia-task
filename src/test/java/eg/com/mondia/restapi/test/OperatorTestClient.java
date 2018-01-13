package eg.com.mondia.restapi.test;
  
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 
import eg.com.mondia.restapi.model.LoOperator;;
  
public class OperatorTestClient {
  
    public static final String REST_SERVICE_URI = "http://localhost:8080/mondia-restapi";
  
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send loOperator-credentials.
     */
    private static HttpHeaders getHeaders(){
        String plainCredentials="mondia:mondia";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
         
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
     
    /*
     * Send a GET request to get list of all loOperators.
     */
    @SuppressWarnings("unchecked")
    private static void listAllLoOperators(){
        System.out.println("\nTesting listAllLoOperators API-----------");
        RestTemplate restTemplate = new RestTemplate(); 
         
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/loOperator/", HttpMethod.GET, request, List.class);
        List<LinkedHashMap<String, Object>> loOperatorsMap = (List<LinkedHashMap<String, Object>>)response.getBody();
         
        if(loOperatorsMap!=null){
            for(LinkedHashMap<String, Object> map : loOperatorsMap){
                System.out.println("LoOperator : id="+map.get("id")+", Name="+map.get("name")+", Description="+map.get("description")+
                		", minPrice="+map.get("minPrice")+", maxPrice="+map.get("maxPrice"));;
            }
        }else{
            System.out.println("No loOperator exist----------");
        }
    }
      
    /*
     * Send a GET request to get a specific loOperator.
     */
    private static void getLoOperator(){
        System.out.println("\nTesting getLoOperator API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<LoOperator> response = restTemplate.exchange(REST_SERVICE_URI+"/loOperator/1", HttpMethod.GET, request, LoOperator.class);
        LoOperator loOperator = response.getBody();
        System.out.println(loOperator);
    }
      
    /*
     * Send a POST request to create a new loOperator.
     */
    private static void createLoOperator() {
        System.out.println("\nTesting create LoOperator API----------");
        RestTemplate restTemplate = new RestTemplate();
        LoOperator loOperator = new LoOperator();
        loOperator.setName("FamilyLoOperator");
        /*loOperator.setDescription("FamilyLoOperator");
        loOperator.setMinPrice(134.0);
        loOperator.setMaxPrice(200.0);*/
        HttpEntity<Object> request = new HttpEntity<Object>(loOperator, getHeaders());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/loOperator/", request, LoOperator.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
  
    /*
     * Send a PUT request to update an existing loOperator.
     */
    private static void updateLoOperator() {
        System.out.println("\nTesting update LoOperator API----------");
        RestTemplate restTemplate = new RestTemplate();
        LoOperator loOperator  = new LoOperator();
        loOperator.setName("P11");
        /*loOperator.setDescription("Desc1");
        loOperator.setMinPrice(33.0);
        loOperator.setMaxPrice(700.0);*/
        HttpEntity<Object> request = new HttpEntity<Object>(loOperator, getHeaders());
        ResponseEntity<LoOperator> response = restTemplate.exchange(REST_SERVICE_URI+"/loOperator/1", HttpMethod.PUT, request, LoOperator.class);
        System.out.println(response.getBody());
    }
  
    /*
     * Send a DELETE request to delete a specific loOperator.
     */
    private static void deleteLoOperator() {
        System.out.println("\nTesting delete LoOperator API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/loOperator/3", HttpMethod.DELETE, request, LoOperator.class);
    }
  
  
   /* 
     * Send a DELETE request to delete all loOperators.
     
    private static void deleteAllLoOperators() {
        System.out.println("\nTesting all delete LoOperators API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/loOperator/", HttpMethod.DELETE, request, LoOperator.class);
    }*/
  
 
    public static void main(String args[]){
         
        
    	
    	createLoOperator();
        listAllLoOperators();
        
    	
 
        getLoOperator();
 
        listAllLoOperators();
 
        updateLoOperator();
        listAllLoOperators();
 
        deleteLoOperator();
        listAllLoOperators();
 
        //deleteAllLoOperators();
        //listAllLoOperators();
    }
}