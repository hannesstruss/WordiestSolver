package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class Solver$Test extends FunSuite {
  val dict = new DumbDictionary
  val solver = new Solver(dict)

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

  test("should calculate score for simple tiles") {
    val word: Word = List(Tile('l'), Tile('o'), Tile('r'), Tile('r'), Tile('y'))
    assert(solver.score(word) == 9)
  }

  test("should calculate score for tiles with letter multipliers") {
    val word = List(Tile('l', 2), Tile('o'), Tile('r'), Tile('r'), Tile('y', 3))
    assert(solver.score(word) == 19)
  }

  test("should calculate score for tiles with word multipliers") {
    val word = List(Tile('d'), Tile('u'), Tile('d'), Tile('e', wordMultiplier = 3))
    assert(solver.score(word) == 21)
  }

  test("should calculate score for tiles with both multipliers") {
    val word = List(Tile('d', letterMultiplier = 2), Tile('u'), Tile('d'), Tile('e', wordMultiplier = 3))
    assert(solver.score(word) == 27)
  }

  test("should calculate score for editorial+wage") {
    val editorial = List(Tile('e',1,1), Tile('d',1,1), Tile('i',1,2), Tile('t',1,1), Tile('o',1,1), Tile('r',4,1), Tile('i',1,1), Tile('a',1,1), Tile('l',1,1))
    assert(solver.score(editorial) == 28)
  }

  test("should solve an easy game") {
    val game = List(Tile('j'), Tile('o', 2), Tile('o'))
    assert(solver.solve(game) == List(Tile('j'), Tile('o', 2)))
  }

  test("should detect overlap for identical tiles in two words") {
    val overlappingTile = Tile('a')
    val word1 = List(overlappingTile, Tile('b'))
    val word2 = List(Tile('c'), overlappingTile)
    assert(solver.overlap(word1, word2))
  }

  test("should not detect overlap for disjoint words") {
    val word1 = List(Tile('a'), Tile('b'))
    val word2 = List(Tile('b'), Tile('c'))
    assert(!solver.overlap(word1, word2))
  }
}
