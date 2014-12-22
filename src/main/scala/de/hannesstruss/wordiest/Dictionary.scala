package de.hannesstruss.wordiest

trait Dictionary {

  def isBeginningOfWord(beginning: Word): Boolean

  def isWord(word: Word): Boolean
}
