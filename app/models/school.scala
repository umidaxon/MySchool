package models

import play.api.db.slick.Config.driver.simple._
import sun.management.snmp.jvmmib.JvmRTBootClassPathTableMeta

case class Pupil(id: Option [Int],
                  ism: String,
                  familiya: String,
                  classId:Int)

case class ClassPupil(pupil: Pupil,
                          className: String)

case class Class(id: Option[Int],
                 name: String)

class PupilTable(tag: Tag) extends Table[Pupil](tag, "pupil") {

  val classes = TableQuery[ClassTable]

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def ism = column[String]("ISM", O.Default(""))

  def familiya = column[String]("FAMILIYA", O.Default(""))

  def classId = column[Int]("CLASS_ID",  O.NotNull)

  def * = (id.?, ism,familiya, classId) <> (Pupil.tupled, Pupil.unapply _)

  def c_lass = foreignKey("PUPIL_FK_CLASS_ID", classId, classes)(_.id)

}

class ClassTable(tag: Tag) extends Table[Class](tag, "CLASS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def * = (id.?, name) <> (Class.tupled, Class.unapply _)

}



