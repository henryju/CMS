package net.onisia.model

import net.liftweb.mapper._
import net.liftweb.http.S

/**
 * A blog entry
 *
 * Users can post blog entries on their blogs. This class
 * represents one instance of a blog entry.
 */
class BlogEntry extends LongKeyedMapper[BlogEntry] with IdPK {
  def getSingleton = BlogEntry

  /** User owning this blog entry */
  object owner extends MappedLongForeignKey(this, User) {
    override def dbIndexed_? = true
  }

  /** Title of the blog entry */
  object title extends MappedString(this,150)

  /** Text of the blog entry */
  object content extends MappedTextarea(this, 5000) {
    override def textareaRows = 100
    override def textareaCols = 80
    override def displayName = S.?("your.text.here")
  }
}


object BlogEntry extends BlogEntry with LongKeyedMetaMapper[BlogEntry] {
  override def dbTableName = "blogs"
}


