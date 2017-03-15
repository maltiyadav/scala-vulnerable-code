package models

import org.joda.time.DateTime

case class Users(
  id:              String,
  name:            Option[String],
  email:           String,
  password:        String,
  createdTime:     DateTime,
  lastUpdatedTime: Option[DateTime]
)
