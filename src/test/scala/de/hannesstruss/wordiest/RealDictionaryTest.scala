package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class RealDictionaryTest extends FunSuite {
  val words = loadWordsFromFile()

  test("should load the dictionary") {
    val dict = new RealDictionary(words)
    assert(dict.isWord(List(Tile('t'), Tile('r'), Tile('e'), Tile('e'))))
  }

  test("empty list should be a beginning of a word") {
    val dict = new RealDictionary(words)
    assert(dict.isBeginningOfWord(Nil))
  }
}
