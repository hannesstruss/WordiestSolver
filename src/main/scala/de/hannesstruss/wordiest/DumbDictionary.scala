package de.hannesstruss.wordiest

class DumbDictionary extends Dictionary {
  private val words = Set("ja", "jo")

  override def isBeginningOfWord(beginning: Word): Boolean = {
    val str = beginning.map(_.letter).mkString
    words.filter(_.startsWith(str)).nonEmpty
  }

  override def isWord(word: Word): Boolean = {
    val str = word.map(_.letter).mkString
    words contains str
  }
}
