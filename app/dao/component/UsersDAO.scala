package dao.component

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton()
class UsersDAO  @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UsersComponent
  with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  /**
    * SQL INJECTION
    * On LN 26, email and password contains user-supplied data,
    * while the remainder is the SQL static part supplied by the programmer
    * making the SQL statement dynamic.
    *
    * Here,
    * Example1:
    * Supplied data for email and password could be anythings like "500 or 1=1"
    *
    * Sql query would be like that:
    * select name, email from users WHERE email='"500 or 1=1"' AND password='"500 or 1=1"'
    *
    * In above sql query, all the records of users table would be fetched becuase 1=1 would be always true.
    *
    *
    *
    * @param email
    * @param password
    * @return
    */

  def login(email: String, password: String): Future[Vector[(String, String)]] = {

    val query = sql"""
             select name, email from users WHERE email='$email' AND password='$password'

    """.as[(String, String)]
    db.run(query)
  }

}

