import scalikejdbc._

object Sample extends App {

  val url = "jdbc:postgresql://localhost:5432/postgres"
  val user = "postgres"
  val password = "postgres"
  ConnectionPool.singleton(url, user, password)

  val names = DB readOnly { implicit session =>
    sql"select usename, application_name, query from pg_stat_activity".map {
      rs => (rs.string(1), rs.string(2), rs.string(3).replaceAll("\\s+", " "))
    }.list.apply()
  }
  names.foreach(println)
}
