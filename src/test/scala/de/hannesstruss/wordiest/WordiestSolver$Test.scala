package de.hannesstruss.wordiest

import de.hannesstruss.wordiest.WordiestSolver.Tile
import org.scalatest.FunSuite

class WordiestSolver$Test extends FunSuite {
  test("should find beginning of word") {
    val w = List(Tile('j'))
    assert(WordiestSolver.isStartOfWord(w))
    assert(!WordiestSolver.isStartOfWord(List(Tile('x'))))
  }

  test("empty word should be the beginning of a word") {
    assert(WordiestSolver.isStartOfWord(List()))
  }

  test("should match complete words") {
    val ja = List(Tile('j'), Tile('a'))
    assert(WordiestSolver.isWord(ja))
  }

  test("should return empty permutation") {
    val result = WordiestSolver.permute(Nil, Nil)
    assert(result == List(Nil))
  }

  test("should match one tile with prefix") {
    val result = WordiestSolver.permute(List(Tile('o')), List(Tile('j')))
    assert(result == List(List(Tile('j'), Tile('o'))))
  }

  test("Should permute some tiles") {
    val in = List(Tile('j'), Tile('a'), Tile('o'))
    val result = WordiestSolver.permute(in)
    assert(result contains List(Tile('j'), Tile('a')))
    assert(result contains List(Tile('j'), Tile('o')))
    assert(result.size == 2)
  }
}
