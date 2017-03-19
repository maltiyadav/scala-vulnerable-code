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
    *
    * @param email
    * @param password
    * @return
    */

  def login(email: String, password: String): Future[Vector[(String, String)]] = {

    // encrypt password
    val encryptPassword = md5Hash(password)

    val query = sql"""
             select name, email from users WHERE email='$email' AND password='$encryptPassword'

    """.as[(String, String)]
    db.run(query)
  }

  def md5Hash(text: String) : String = {
    MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}
  }

}

