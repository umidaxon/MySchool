package controllers

import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger
import play.api.libs.json.Json._
import play.api.libs.json.Json
/**
 * Created by Acer on 13.04.2015.
 */
class Classes extends Controller {
  val pupils = TableQuery[ClassTable]

  def list = DBAction { implicit rs =>
    Ok(views.html.listSchools(pupils.list))
  }
  def showAddForm = DBAction { implicit rs =>
    Ok(views.html.addSchools())
  }

  def add = DBAction { implicit request =>
    val  classParams = request.body.asFormUrlEncoded
    val name = classParams.get("name")(0)
    val classId = (pupils returning pupils.map(_.id)) += Class(None, name)
    Logger.info(s"LastId = $classId")
    Redirect(routes.Classes.list())
  }
  def remove(id: Int) = DBAction { implicit request =>
    pupils.filter(_.id === id).delete;
    Redirect(routes.Classes.list())
  }
  def update(id: Int) = DBAction { implicit rs =>
    val classParams = rs.body.asFormUrlEncoded
    val name = classParams.get("name")(0)

    val pupil = Class(Some(id), name)
    pupils.filter(_.id === id).update(pupil)

    Redirect(routes.Pupils.list())
  }
  def showEditForm(pupilId: Int) = DBAction { implicit  request =>
    val byId = pupils.findBy(_.id)
    val classes = byId(pupilId).list.head

    Ok(views.html.editschool(classes))
  }
}

