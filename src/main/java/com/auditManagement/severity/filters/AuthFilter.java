package com.auditManagement.severity.filters;

import com.auditManagement.severity.models.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Autowired private RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
    	String authUri = "http://localhost:7000/api/authjwt";
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("Authorization", request.getHeader("Authorization"));
	    HttpEntity <String> entity = new HttpEntity<>(headers);
	    try {
		    restTemplate.exchange(authUri, HttpMethod.GET, entity, ErrorResponse.class);
		    filterChain.doFilter(request, response);
	    } catch(Exception exception) {
	    	log.info(exception.getMessage());
	    	exceptionHandler(exception,response);
	    }
    }
    
    private static void exceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
    	log.info(exception.getMessage());
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		ErrorResponse error = mapper.readValue(exception.getMessage().substring(7,exception.getMessage().length()-1),ErrorResponse.class);
        	response.setStatus(Integer.parseInt(exception.getMessage().substring(0,3)));
        	String json = mapper.writeValueAsString(error);
        	response.setContentType("application/json");
            response.getWriter().write(json);
    	} catch(Exception e) {
    		log.error(e.getMessage());
			ErrorResponse error = new ErrorResponse(8006,"Auth server error");
			response.setStatus(500);
			String json = mapper.writeValueAsString(error);
        	response.setContentType("application/json");
            response.getWriter().write(json);
    	}
    }
}
