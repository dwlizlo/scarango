package com.outr.arango

import io.circe.Json

case class BackingStore(data: Json, _id: Id[BackingStore]) extends Document[BackingStore]

object BackingStore extends DocumentModel[BackingStore] {
  override def indexes: List[Index] = Nil

  override val collectionName: String = "backingStore"
  override implicit val serialization: Serialization[BackingStore] = Serialization.auto[BackingStore]
}