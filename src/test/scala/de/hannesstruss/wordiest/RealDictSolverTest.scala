package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class RealDictSolverTest extends FunSuite {
  val words = loadWordsFromFile()
  val dict = new RealDictionary(words)
  val solver = new Solver(dict)
  val parser = new Parser

  test("it should find the word tree") {
    val game = List(Tile('r'), Tile('e'), Tile('e'), Tile('t'), Tile('x'))
    assert(solver.permute(game) contains List(Tile('t'), Tile('r'), Tile('e'), Tile('e')))
  }

  test("should find tie") {
    val game = parser.parse("t i e r")
    assert(solver.permute(game) contains game)
  }

  test("should find teriyaki") {
    val teriyaki = parser.parse("t e r i y a k i")
    assert(dict.isWord(teriyaki))

    assert(solver.permute(teriyaki).map(solver.pretty) contains "teriyaki")
  }

  test("should solve iota+clergy optimally") {
    // should be gracility + tie
    val game = parser.parse("o c i y,2l l g,2w r e t a i t i e")

    val (a, b) = solver.solveTwo(game)
    println(a)
    println(b)
    assert(solver.score(a) + solver.score(b) >= 45)
  }

  test("should solve teriyaki optimally") {
    val game = parser.parse("t o k,3l r i t d y,4l e,3w a i a e a")

    val (a, b) = solver.solveTwo(game)
    println(a)
    println(b)
    assert(solver.score(a) + solver.score(b) >= 116)
  }
}
