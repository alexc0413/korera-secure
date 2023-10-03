package com.example.korera.controller;
import com.example.korera.entity.ResourceDetail;
import com.example.korera.service.ResourceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/resourcedetail")
public class ResourceDetailController {
    private final ResourceDetailService resourceDetailService;

    @Autowired
    public ResourceDetailController(ResourceDetailService resourceDetailService){
        this.resourceDetailService = resourceDetailService;
    }


    // have to make sure Resource has already existed and must use persisted resource object
    @PostMapping("/create")
    public ResponseEntity<ResourceDetail> createResource(@RequestBody ResourceDetail resourceDetail){
        ResourceDetail resourceDetail1 = resourceDetailService.createResourceDetail(resourceDetail);
        return new ResponseEntity<>(resourceDetail1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Integer id){
        resourceDetailService.deleteResourceDetailById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<ResourceDetail> updateResource(@RequestBody ResourceDetail resourceDetail){
        ResourceDetail r  = resourceDetailService.updateResourceDetail(resourceDetail);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResourceDetail> getResourceById(@PathVariable Integer id){
        ResourceDetail r  = resourceDetailService.getResourceDetailById(id);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ResourceDetail>> getAll(){
        List<ResourceDetail> resources = resourceDetailService.getAll();
        return new ResponseEntity<>(resources,HttpStatus.OK);
    }
}
