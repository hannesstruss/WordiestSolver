package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class WordiestSolver$Test extends FunSuite {
  val dict = new DumbDictionary
  val solver = new WordiestSolver(dict)

  test("should find beginning of word") {
    val w = List(Tile('j'))
    assert(dict.isBeginningOfWord(w))
    assert(!dict.isBeginningOfWord(List(Tile('x'))))
  }

  test("empty word should be the beginning of a word") {
    assert(dict.isBeginningOfWord(List()))
  }

  test("should match complete words") {
    val ja = List(Tile('j'), Tile('a'))
    assert(dict.isWord(ja))
  }

  test("should return empty permutation") {
    val result = solver.permute(Nil, Nil)
    assert(result == List(Nil))
  }

  test("should match one tile with prefix") {
    val result = solver.permute(List(Tile('o')), List(Tile('j')))
    assert(result == List(List(Tile('j'), Tile('o'))))
  }

  test("Should permute some tiles") {
    val in = List(Tile('j'), Tile('a'), Tile('o'))
    val result = solver.permute(in)
    assert(result contains List(Tile('j'), Tile('a')))
    assert(result contains List(Tile('j'), Tile('o')))
    assert(result.size == 2)
  }
}
