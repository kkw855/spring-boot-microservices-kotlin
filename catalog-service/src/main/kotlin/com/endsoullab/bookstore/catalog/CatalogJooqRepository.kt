package com.endsoullab.bookstore.catalog

import org.springframework.stereotype.Repository
import com.endsoullab.bookstore.catalog.jooq.tables.references.PRODUCTS
import org.jooq.DSLContext

@Repository
class CatalogJooqRepository(private val dsl: DSLContext) {
  fun findAll(): Product {
    return dsl.select(
      PRODUCTS.CODE,
      PRODUCTS.NAME,
      PRODUCTS.DESCRIPTION,
      PRODUCTS.IMAGE_URL,
      PRODUCTS.PRICE
    )
      .from(PRODUCTS)
      .where(PRODUCTS.ID.eq(1))
      .fetchOne{ it.into(Product::class.java) }!!
  }
}
