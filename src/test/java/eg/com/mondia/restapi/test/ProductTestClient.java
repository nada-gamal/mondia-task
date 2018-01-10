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
 
import eg.com.mondia.restapi.model.Product;;
  
public class ProductTestClient {
  
    public static final String REST_SERVICE_URI = "http://localhost:8080/mondia-restapi";
  
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send product-credentials.
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
     * Send a GET request to get list of all products.
     */
    @SuppressWarnings("unchecked")
    private static void listAllProducts(){
        System.out.println("\nTesting listAllProducts API-----------");
        RestTemplate restTemplate = new RestTemplate(); 
         
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/product/", HttpMethod.GET, request, List.class);
        List<LinkedHashMap<String, Object>> productsMap = (List<LinkedHashMap<String, Object>>)response.getBody();
         
        if(productsMap!=null){
            for(LinkedHashMap<String, Object> map : productsMap){
                System.out.println("Product : id="+map.get("id")+", Name="+map.get("name")+", Description="+map.get("description")+
                		", minPrice="+map.get("minPrice")+", maxPrice="+map.get("maxPrice"));;
            }
        }else{
            System.out.println("No product exist----------");
        }
    }
      
    /*
     * Send a GET request to get a specific product.
     */
    private static void getProduct(){
        System.out.println("\nTesting getProduct API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<Product> response = restTemplate.exchange(REST_SERVICE_URI+"/product/1", HttpMethod.GET, request, Product.class);
        Product product = response.getBody();
        System.out.println(product);
    }
      
    /*
     * Send a POST request to create a new product.
     */
    private static void createProduct() {
        System.out.println("\nTesting create Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product();
        product.setName("FamilyProduct");
        product.setDescription("FamilyProduct");
        product.setMinPrice(134.0);
        product.setMaxPrice(200.0);
        HttpEntity<Object> request = new HttpEntity<Object>(product, getHeaders());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/product/", request, Product.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
  
    /*
     * Send a PUT request to update an existing product.
     */
    private static void updateProduct() {
        System.out.println("\nTesting update Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product  = new Product();
        product.setName("P11");
        product.setDescription("Desc1");
        product.setMinPrice(33.0);
        product.setMaxPrice(700.0);
        HttpEntity<Object> request = new HttpEntity<Object>(product, getHeaders());
        ResponseEntity<Product> response = restTemplate.exchange(REST_SERVICE_URI+"/product/1", HttpMethod.PUT, request, Product.class);
        System.out.println(response.getBody());
    }
  
    /*
     * Send a DELETE request to delete a specific product.
     */
    private static void deleteProduct() {
        System.out.println("\nTesting delete Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/product/3", HttpMethod.DELETE, request, Product.class);
    }
  
  
   /* 
     * Send a DELETE request to delete all products.
     
    private static void deleteAllProducts() {
        System.out.println("\nTesting all delete Products API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/product/", HttpMethod.DELETE, request, Product.class);
    }*/
  
 
    public static void main(String args[]){
         
        
    	
    	createProduct();
        listAllProducts();
        
    	
 
        getProduct();
 
        listAllProducts();
 
        updateProduct();
        listAllProducts();
 
        deleteProduct();
        listAllProducts();
 
        //deleteAllProducts();
        //listAllProducts();
    }
}