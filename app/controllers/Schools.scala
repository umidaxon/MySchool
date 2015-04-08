package controllers


import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger

import scala.slick.lifted.TableQuery

class Schools extends Controller{

  val teachers = TableQuery[TeacherTable]

  def list = DBAction { implicit req =>
    Logger.info(s"SHOW_ALL=${teachers.list}")
    Ok(views.html.list(teachers.list))
  }

  def addform = Action {
    Ok(views.html.add())
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val ism = formParams.get("ism")(0)
    val familiya = formParams.get("familiya")(0)
    val fan = formParams.get("fan")(0)

    println("Name: " + ism)
    println("Name: " + ism)
    val teacherId = (teachers returning teachers.map(_.id)) += School(None, ism, familiya, fan)
    Redirect(routes.Schools.list())
  }

  def remove(id: Int) = DBAction { implicit request =>
    teachers.filter(_.id === id).delete;
    Redirect(routes.Schools.list())
  }
}