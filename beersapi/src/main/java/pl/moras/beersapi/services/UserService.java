package pl.moras.beersapi.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@FeignClient(name = "AuthenticationService")
public interface UserService {

    @RequestMapping("/auth/id")
    Map<String, Integer> getId(@RequestHeader("Authorization") String token);

    @RequestMapping("/auth/roles")
    Collection<String> getRoles(@RequestHeader("Authorization") String token);

}
