package com.endsoullab.bookstore.catalog

import java.math.BigDecimal

data class Product(
  val code: String,
  val name: String,
  val description: String?,
  val imageUrl: String?,
  val price: BigDecimal
)
