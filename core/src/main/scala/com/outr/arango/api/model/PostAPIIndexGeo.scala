package com.outr.arango.api.model

import io.circe.Json

/**
  * PostAPIIndexGeo
  *
  * @param type must be equal to *"geo"*.
  * @param fields An array with one or two attribute paths.
  *        
  *        If it is an array with one attribute path *location*, then a geo-spatial
  *        index on all documents is created using *location* as path to the
  *        coordinates. The value of the attribute must be an array with at least two
  *        double values. The array must contain the latitude (first value) and the
  *        longitude (second value). All documents, which do not have the attribute
  *        path or with value that are not suitable, are ignored.
  *        
  *        If it is an array with two attribute paths *latitude* and *longitude*,
  *        then a geo-spatial index on all documents is created using *latitude*
  *        and *longitude* as paths the latitude and the longitude. The value of
  *        the attribute *latitude* and of the attribute *longitude* must a
  *        double. All documents, which do not have the attribute paths or which
  *        values are not suitable, are ignored.
  * @param geoJson If a geo-spatial index on a *location* is constructed
  *        and *geoJson* is *true*, then the order within the array is longitude
  *        followed by latitude. This corresponds to the format described in
  *        http://geojson.org/geojson-spec.html#positions
  *
  * WARNING: This code is generated by youi-plugin's generateHttpClient. Do not modify directly.
  */
case class PostAPIIndexGeo(`type`: String,
                           fields: Option[List[String]] = None,
                           geoJson: Option[String] = None)