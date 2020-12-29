package com.app.mycoolapp;

import com.app.mycoolapp.dto.SignUpResponse;
import com.app.mycoolapp.dto.SignupModel;
import com.app.mycoolapp.service.DriverService;
import com.app.mycoolapp.service.PassengerService;
import com.app.mycoolapp.service.SignUpService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTests {

    @InjectMocks
    public SignUpService signUpService;

    SignupModel signupModel;

    @Before
    public void setup() {
        signupModel = new SignupModel("driver", "soumya", "989839148", "male", "D123");
    }

    @Test
    public void testSignUp() {
        SignUpResponse signUpResponse = signUpService.signup(signupModel);
//        Assert.assse
//        Mockito.when(repository.findByUserId(Mockito.eq(userId))).thenReturn(Optional.empty());
//        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(testUser);
//        User savedUser= userService.save(testUser);
//        Assert.assertEquals(testUser,savedUser);
//        Mockito.verify(repository).findByUserId(userId);
//        Mockito.verify(passwordEncoder,Mockito.times(1)).encode(testUser.getPassword());
//        Mockito.verify(roleRepository).findByName("ROLE_USER");
    }
}
