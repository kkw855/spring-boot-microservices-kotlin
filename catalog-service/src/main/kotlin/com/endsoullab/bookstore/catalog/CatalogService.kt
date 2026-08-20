package com.endsoullab.bookstore.catalog

import org.springframework.stereotype.Service

@Service
class CatalogService(private val jooqRepository: CatalogJooqRepository) {
  fun getProducts(): Product {
    return jooqRepository.findAll()
  }
}
