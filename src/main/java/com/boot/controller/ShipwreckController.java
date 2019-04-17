package com.boot.controller;


import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class ShipwreckController {

  @Autowired
  private ShipwreckRepository shipwreckRepository;

  @GetMapping(value = "shipwrecks")
  List<Shipwreck> list() {
    return shipwreckRepository.findAll();
  }

  @PostMapping("shipwrecks")
  public Shipwreck create(@RequestBody Shipwreck shipwreck) {
    return shipwreckRepository.saveAndFlush(shipwreck);
  }

  @GetMapping("shipwrecks/{id}")
  public Shipwreck get(@PathVariable Long id) {
    return shipwreckRepository.findById(id).get();
  }

  @PutMapping("shipwrecks/{id}")
  public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
    Optional<Shipwreck> byId = shipwreckRepository.findById(id);
    BeanUtils.copyProperties(shipwreck, byId.get());
    return shipwreckRepository.saveAndFlush(byId.get());

  }

  @DeleteMapping("shipwrecks/{id}")
  public Shipwreck delete(@PathVariable Long id) {
    Shipwreck shipwreck = shipwreckRepository.findById(id).get();
    shipwreckRepository.delete(shipwreck);
    return shipwreck;
  }
}
