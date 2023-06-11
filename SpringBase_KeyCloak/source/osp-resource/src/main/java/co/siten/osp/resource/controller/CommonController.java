package co.siten.osp.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@Slf4j
public class CommonController {


    @GetMapping("/authorize")
    public ResponseEntity<String> authorize()
    {
        log.debug("REST request to authorize");
        return ResponseEntity.ok("Authenticated");
    }

    @RolesAllowed({ "user", "admin"})
    @GetMapping("/user/login")
    public ResponseEntity<String> getUser()
    {
        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed({"admin"})
    @GetMapping("/admin/login")
    public ResponseEntity<String> getAdmin()
    {
        return ResponseEntity.ok("Hello Admin");
    }
}
