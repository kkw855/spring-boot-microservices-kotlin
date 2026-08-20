package com.endsoullab.bookstore.catalog

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class CatalogController(private val catalogService: CatalogService) {
  @GetMapping
  fun getProducts(): ResponseEntity<Product> {
    return ResponseEntity.ok(catalogService.getProducts())
  }
}
