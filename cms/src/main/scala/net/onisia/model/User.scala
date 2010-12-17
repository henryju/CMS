package net.onisia.model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.sitemap._
import net.liftweb.http.S
import net.liftweb.sitemap.Loc._

/**
 * The singleton that has methods for accessing the database
 */
object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "users"
  override def screenWrap = Full(<lift:surround with="default" at="content">
			       <lift:bind /></lift:surround>)
  // define the order fields will appear in forms and output
  override def fieldOrder = List(id, firstName, lastName, email,
  locale, timezone, password, textArea)

  // comment this line out to require email validations
  override def skipEmailValidation = true

  override def menus = blogMenuLoc.toList ::: super.menus
  
  /** 
   * The menu item for blog 
   */  
  def blogMenuLoc: Box[Menu] =  
    Full(Menu(Loc("Blog", List("blog"), S.?("blog"), testLogginIn :: Nil)))  
}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class User extends MegaProtoUser[User] {
  def getSingleton = User // what's the "meta" server

  // define an additional field for a personal essay
  object textArea extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Personal Essay"
  }

  /**
   * Retrieves blog entries for this user
   */
  def blogs = BlogEntry.findAll(By(BlogEntry.owner, this.id))
}
