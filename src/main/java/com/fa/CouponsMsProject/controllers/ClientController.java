package com.fa.CouponsMsProject.controllers;

import com.fa.CouponsMsProject.beans.ClientType;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.dto.AccessDTO;
import com.fa.CouponsMsProject.dto.ClientDto;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.exceptions.SecurityException;
import com.fa.CouponsMsProject.facades.ClientFacade;
import com.fa.CouponsMsProject.mappers.ClientModelMapper;
import com.fa.CouponsMsProject.mappers.CouponModelMapper;
import com.fa.CouponsMsProject.model.request.ClientLoginRequestModel;
import com.fa.CouponsMsProject.model.request.ClientRegisterRequestModel;
import com.fa.CouponsMsProject.model.response.ClientLoginResponseModel;
import com.fa.CouponsMsProject.model.response.ClientRegisterResponseModel;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.security.access.AccessManager;
import com.fa.CouponsMsProject.security.register.RegisterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ALLOWED_HEADERS;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ORIGINS;
import static com.fa.CouponsMsProject.security.tokenization.TokenManager.getExpirationInterval;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = ORIGINS, allowedHeaders = ALLOWED_HEADERS)
public class ClientController {

    @Autowired
    protected AccessManager accessManager;

    @Autowired
    protected RegisterManager registerManager;

    @Autowired
    protected CouponModelMapper couponModelMapper;

    @Autowired
    private ClientModelMapper clientModelMapper;

    @Autowired
    private AdminRepository adminRepository;

    protected ClientFacade clientFacade;

    @PostMapping("register")
    public ResponseEntity<ClientRegisterResponseModel> register(@RequestBody ClientRegisterRequestModel request) throws CustomException {
        registerManager.register(request);
        return new ResponseEntity<>(new ClientRegisterResponseModel(request.getEmail()), HttpStatus.CREATED);
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<ClientLoginResponseModel> login(@RequestBody ClientLoginRequestModel request) throws SecurityException {
        AccessDTO accessDTO = accessManager.login(request.getEmail(), request.getPassword());
        clientFacade = accessDTO.getFacade();
        ClientLoginResponseModel response = ClientLoginResponseModel.builder()
                .id(clientFacade.getClient().getId())
                .email(request.getEmail())
                .name(clientFacade.getClient().getName())
                .lastName(clientFacade.getClient().getLastName())
                .clientType(clientFacade.getClientType())
                .token(accessDTO.getToken())
                .isActive(clientFacade.getClient() instanceof Company ? ((Company) clientFacade.getClient()).isActive() : true)
                .idleDisconnectIntervalInMillis(getExpirationInterval())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("logout")
    public ResponseEntity<String> logout(@RequestHeader("authorization") String token) throws SecurityException {
        return new ResponseEntity<>(accessManager.logout(token) + " logged out", HttpStatus.OK);
    }

    @GetMapping("client/info")
    public ResponseEntity<ClientDto> clientInfo(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws SecurityException {
        return new ResponseEntity<>(clientModelMapper.entityToDto(accessManager.getSession(token, clientType, ClientType.ANY).getClient()), HttpStatus.OK);
    }

    @PutMapping("extendtoken")
    public ResponseEntity<Boolean> extendToken(@RequestHeader("authorization") String token) throws SecurityException {
        return new ResponseEntity<>(accessManager.clientMadeAction(token), HttpStatus.OK);
    }

}