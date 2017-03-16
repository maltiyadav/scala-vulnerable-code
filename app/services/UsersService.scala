package services

import com.google.inject.Inject
import dao.component.UsersDAO


class UsersService @Inject() (usersDAO: UsersDAO) {

  def validateLogin(email: String, password: String) = {
    usersDAO.login(email, password)
  }

}
