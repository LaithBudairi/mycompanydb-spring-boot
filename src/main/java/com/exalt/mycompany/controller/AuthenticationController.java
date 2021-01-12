package com.exalt.mycompany.controller;

import com.exalt.mycompany.model.AuthenticationRequest;
import com.exalt.mycompany.model.AuthenticationResponse;
import com.exalt.mycompany.service.UserDetailsServiceImp;
import com.exalt.mycompany.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RequestMapping(value = "api/v1/")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            jwtTokenUtil.setSecretKey(passwordEncoder.encode(authenticationRequest.getPassword()));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

// custom claim
//        Function <Claims, String> test = claims -> (String) claims.get("Test");
//        String tt = jwtTokenUtil.extractClaim(jwt, test);
//        System.out.println(tt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
