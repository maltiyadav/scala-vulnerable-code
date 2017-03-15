package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UsersController @Inject() extends Controller{

  def login = Action.async(parse.json){ implicit request =>

    val email = request.body \ "email"
    val password = request.body \ "password"

    Future(Ok)
  }

}
