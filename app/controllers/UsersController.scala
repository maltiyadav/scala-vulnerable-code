package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.UsersService
import utils.JsLookupUtils

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UsersController @Inject()(usersService: UsersService) extends Controller with JsLookupUtils{

  def login = Action.async(parse.json){ implicit request =>

    val email = request.body \ "email"
    val password = request.body \ "password"

    usersService.validateLogin(email, password)


    Future(Ok)
  }

}
