package models

import play.api.db.slick.Config.driver.simple._

case class School(id: Option [Int], ism: String, familiya: String, fan: String)

class TeacherTable(tag: Tag) extends Table[School](tag, "school") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def ism = column[String]("ism", O.Default(""))

  def familiya = column[String]("familiya", O.Default(""))

  def fan = column[String]("fan", O.Default(""))

  def * = (id.?, ism,familiya, fan) <> (School.tupled, School.unapply _)
}



