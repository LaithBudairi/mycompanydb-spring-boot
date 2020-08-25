package com.exalt.mycompany;

import com.exalt.mycompany.model.Address;
import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.repository.UserProfileRepository;
import com.exalt.mycompany.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MycompanyApplicationTests {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testEmbeddedAddress1And2_InsertsAddress1And2_SuccessfullyInserted() {
        UserProfile profile = userProfileRepository.findById(1).get();

        Address a = new Address();

        a.setCity("Austin1");
        a.setStreet("Spicewood Springs1");
        a.setCountry("USA1");
        a.setState("Texas1");
        a.setZipcode("787501111");

        profile.setAddress1(a);

        a = new Address();

        a.setCity("Austin2");
        a.setStreet("Spicewood Springs2");
        a.setCountry("USA2");
        a.setState("Texas2");
        a.setZipcode("787502222");

        profile.setAddress2(a);

        userProfileRepository.save(profile);


    }

}
