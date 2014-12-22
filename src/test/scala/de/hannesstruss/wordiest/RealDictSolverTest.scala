package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class RealDictSolverTest extends FunSuite {
  val words = loadWordsFromFile()
  val dict = new RealDictionary(words)

  test("it should find the word tree") {
    val game = List(Tile('r'), Tile('e'), Tile('e'), Tile('t'), Tile('x'))
    val solver = new Solver(dict)
    assert(solver.permute(game) contains List(Tile('t'), Tile('r'), Tile('e'), Tile('e')))
  }
}
