package de.hannesstruss.wordiest

trait Dictionary {
  def wordToString(word: Word): String = word.map(_.letter).mkString

  def isBeginningOfWord(beginning: Word): Boolean

  def isWord(word: Word): Boolean
}
