import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "postgres_qi4j"
  val appVersion      = "1.0-SNAPSHOT"
  
  val appDependencies = Seq(
      javaCore,
      
      "org.qi4j.core" % "org.qi4j.core.bootstrap" % "2.0",
      "org.qi4j.core" % "org.qi4j.core.runtime" % "2.0",
      "org.qi4j.core" % "org.qi4j.core.testsupport" % "2.0",
      "org.qi4j.tool" % "org.qi4j.tool.envisage" % "2.0",
      "org.qi4j.tool" % "org.qi4j.tool.entity-viewer" % "2.0",
      "org.qi4j.extension" % "org.qi4j.extension.entitystore-sql" % "2.0",
      "org.qi4j.extension" % "org.qi4j.extension.indexing-sql" % "2.0",
      "org.qi4j.extension" % "org.qi4j.extension.valueserialization-orgjson" % "2.0",
      "org.qi4j.library" % "org.qi4j.library.sql-dbcp" % "2.0",
      "org.qi4j.library" % "org.qi4j.library.rdf" %  "2.0",
      
      "org.codeartisans" % "playqi" % "1.1",

      "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers ++= Seq(
          DefaultMavenRepository,
          "qi4j-releases" at "https://repository-qi4j.forge.cloudbees.com/release/",
          "qi4j-snapshots" at "https://repository-qi4j.forge.cloudbees.com/snapshot/"
      )
  )

}
