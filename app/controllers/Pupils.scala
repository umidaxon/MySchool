package controllers


import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger
import play.api.libs.json.Json._
import play.api.libs.json.Json

import scala.slick.lifted.TableQuery

class Pupils extends Controller{

  val pupils = TableQuery[PupilTable]
  val classes = TableQuery[ClassTable]

  def list = DBAction { implicit rs =>
    //Logger.info(s"SHOW_ALL=${teachers.list}")
    val pupilResult = (for{
      (pupil, c_lass) <- pupils leftJoin  classes on (_.classId ===_.id)
    } yield (pupil, c_lass.name)).list
      .map { case(pupil, pupilName) => ClassPupil(pupil, pupilName)}

    Ok(views.html.list(pupilResult))
  }

  def addform = DBAction { implicit rs =>
    Ok(views.html.add(classes.list))
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val ism = formParams.get("ism")(0)
    val familiya = formParams.get("familiya")(0)
    val classId = formParams.get("class")(0).toInt

    val pupilId = (pupils returning pupils.map(_.id)) += Pupil(None, ism, familiya, classId)
    Logger.info(s"LastId = $pupilId")
    Redirect(routes.Pupils.list())
  }

  def remove(id: Int) = DBAction { implicit request =>
    pupils.filter(_.id === id).delete;

    Redirect(routes.Pupils.list())
  }
  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val ism = formParams.get("ism")(0)
    val familiya = formParams.get("familya")(0)
    val classId = formParams.get("class")(0).toInt

    val pupil = Pupil(Some(id), ism, familiya, classId)
    pupils.filter(_.id === id).update(pupil)

    Redirect(routes.Pupils.list())
  }
  def showEditForm(pupilId: Int) = DBAction { implicit request =>
    val byId = pupils.findBy(_.id)
    val pupil = byId(pupilId).list.head

    Ok(views.html.edit(pupil, classes.list))
  }

}