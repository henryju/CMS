package net.onisia.snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.common._
import net.liftweb.http.S

import net.onisia.model.User

class Blog {
  def menu(in:NodeSeq): NodeSeq = User.currentUser match {
    case Full(user) => Text(S.?("blog.welcome"))
    case _ => Text("You have to be logged to use the blog")
  }
}
