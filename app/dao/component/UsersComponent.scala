package dao.component

import models.Users
import org.joda.time.DateTime
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

trait UsersComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class UsersTable(tag: Tag) extends Table[Users](tag, "users") {
    def id = column[String]("id", O.PrimaryKey)

    def name = column[String]("name")

    def email = column[String]("email")

    def password = column[String]("password")

    def createdTime = column[Option[DateTime]]("created_ts")

    def lastUpdatedTime = column[Option[DateTime]]("last_updated_ts")

    def * = (id, name, email, password, createdTime, lastUpdatedTime) <> ((Users.apply _).tupled, Users.unapply)
  }

}

