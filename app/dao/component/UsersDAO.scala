package dao.component

import java.security.MessageDigest
import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton()
class UsersDAO  @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UsersComponent
  with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  /**
    * @param email
    * @param password
    * @return
    */

  def login(email: String, password: String): Future[Vector[(String, String)]] = {

    //sanitize input data
    val sanitizeEmail = sanitizeInput(email)

    // encrypt password
    val encryptPassword = md5Hash(password)

    val query = sql"""
             select name, email from users WHERE email=$sanitizeEmail AND password= $encryptPassword
    """.as[(String, String)]
    db.run(query)
  }

  def md5Hash(text: String) : String = {
    MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}
  }

  def sanitizeInput(input: String) = {
    input.replace("\"", "").replace("\'", "").replace("\\", "").trim
  }
}

