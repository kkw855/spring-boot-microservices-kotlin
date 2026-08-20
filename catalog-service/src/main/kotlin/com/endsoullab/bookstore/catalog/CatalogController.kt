package com.endsoullab.bookstore.catalog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CatalogController {
  @GetMapping
  fun getProducts(): String {
    return "Hello Catalog"
  }
}
