package com.outr.arango.api

import com.outr.arango.api.model._
import io.youi.client.HttpClient
import io.youi.http.HttpMethod
import io.youi.net._
import io.circe.Json
import scala.concurrent.{ExecutionContext, Future}
      
object APIReplicationMakeSlave {

  def put(client: HttpClient, body: PutAPIReplicationMakeSlave)(implicit ec: ExecutionContext): Future[Json] = client
    .method(HttpMethod.Put)
    .path(path"/_api/replication/make-slave", append = true) 
    .restful[PutAPIReplicationMakeSlave, Json](body)
}