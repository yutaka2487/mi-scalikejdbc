import scalikejdbc._

object Sample extends App {

  val url = "jdbc:postgresql://localhost:5432/postgres"
  val user = "postgres"
  val password = "postgres"
  ConnectionPool.singleton(url, user, password)

  val names = DB readOnly { implicit session =>
    sql"select application_name from pg_stat_activity".map(_.string("application_name")).list.apply()
  }
  names.foreach(println)
}
