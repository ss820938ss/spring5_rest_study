package spring5_rest_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring5_rest_study.service.RemoveMemberService;

@RestController
@RequestMapping("/api")
public class RestRemoveMemberController {
	
	@Autowired
    private RemoveMemberService service;

	@DeleteMapping("/members/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable long id){
        System.out.println("deleteMember > " + id);
        return ResponseEntity.ok(service.removeMember(id));
    }

}
