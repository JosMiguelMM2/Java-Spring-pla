package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
  @Autowired
  private PurchaseService purchaseService;


  @GetMapping("/all")
  public ResponseEntity<List<Purchase>> getAll() {
    return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{Id}")
  public ResponseEntity<List<Purchase>> getByClient(@PathVariable("Id") String clientId){
    Optional<List<Purchase>> purchases = purchaseService.getByClient(clientId);

    if (purchases.isPresent() && !purchases.get().isEmpty()) {
      return new ResponseEntity<>(purchases.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
    return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
  }

  //tiene una elminiacion qu depende otros servicios por lo cual no funciona
  @DeleteMapping("/delete/{Id}")
  public ResponseEntity delete(@PathVariable("Id") String clientId){
    if(purchaseService.delete(clientId)){
      return new ResponseEntity(HttpStatus.OK);
    }else{
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
