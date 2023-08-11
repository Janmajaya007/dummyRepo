package com.janmajaya.Movie_Booking_Application.controller;

//import com.moviebookingapp.moviebookingapp.model.UserDTO;
import com.janmajaya.Movie_Booking_Application.model.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@RequestMapping("call/consumer")
@CrossOrigin("*")
public class ConsumerController
{
	@PostMapping(value="/login")
	public ResponseEntity<?> performLogin(@RequestBody UserDTO userdto) throws RestClientException, Exception
	{
		String baseUrl = "http://localhost:8084/auth/v1/login";

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Map<String,String>> result;
try
	{
		result = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<Map<String,String>>() {});
	}
	catch(Exception e)
	{
		return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);

	}
	return new ResponseEntity<Map<String,String>>(result.getBody(), HttpStatus.OK);

	}

	private HttpEntity getHeaders(UserDTO userdto) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<UserDTO>(userdto, headers);
	}
	

}





