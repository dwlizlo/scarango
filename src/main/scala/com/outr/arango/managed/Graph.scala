package com.outr.arango.managed

import com.outr.arango.{Arango, ArangoCursor, ArangoDB, ArangoGraph, ArangoSession}
import io.youi.net.URL

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class Graph(name: String,
            db: String = "_system",
            url: URL = Arango.defaultURL,
            username: String = Arango.defaultUsername,
            password: String = Arango.defaultPassword,
            timeout: FiniteDuration = 15.seconds) {
  private[managed] lazy val arango: Arango = new Arango(url)
  private[managed] lazy val sessionFuture: Future[ArangoSession] = arango.auth(username, password)
  private[managed] lazy val dbFuture: Future[ArangoDB] = sessionFuture.map(_.db(db))
  private[managed] lazy val graphFuture: Future[ArangoGraph] = dbFuture.map(_.graph(name))

  private[managed] lazy val instance: ArangoGraph = Await.result[ArangoGraph](graphFuture, timeout)

  /**
    * Initializes this graph instance, database, and session creating the graph if it doesn't already exist.
    *
    * @param autoCreate automatically creates the graph if it doesn't already exist if set to true. Defaults to true.
    * @return true if the operation completed without error
    */
  def init(autoCreate: Boolean = true): Future[Boolean] = graphFuture.flatMap { graph =>
    graph.exists().flatMap {
      case Some(response) => Future.successful(!response.error)
      case None if autoCreate => graph.create().map(!_.error)
      case None => Future.successful(true)
    }
  }

  /**
    * Deletes the graph.
    *
    * @param dropCollections true if the collections should also be dropped. Defaults to true.
    * @return true if the operation completed successfully
    */
  def delete(dropCollections: Boolean = true): Future[Boolean] = graphFuture.flatMap { graph =>
    graph.delete(dropCollections).map(!_.error)
  }

  def cursor: ArangoCursor = instance.db.cursor
}