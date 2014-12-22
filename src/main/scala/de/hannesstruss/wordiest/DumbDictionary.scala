package de.hannesstruss.wordiest

class DumbDictionary extends Dictionary {
  private val words = Set("ja", "jo")

  override def isBeginningOfWord(beginning: Word): Boolean = {
    words.filter(_.startsWith(wordToString(beginning))).nonEmpty
  }

  override def isWord(word: Word): Boolean = {
    words contains wordToString(word)
  }
}
