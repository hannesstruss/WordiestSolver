package de.hannesstruss.wordiest

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  val parser = new Parser()

  test("should parse a simple string") {
    val input = "h a l l o"
    val result = parser.parse(input)
    assert(result == List(Tile('h'), Tile('a'), Tile('l'), Tile('l'), Tile('o')))
  }

  test("should parse a string with letter multipliers") {
    val input = "h a l,5l l o,2l"
    val expected = List(
      Tile('h'),
      Tile('a'),
      Tile('l', letterMultiplier = 5),
      Tile('l'),
      Tile('o', letterMultiplier = 2)
    )
    assert(parser.parse(input) == expected)
  }

  test("should parse a string with word multipliers") {
    val input = "y o,5w"
    val expected = List(Tile('y'), Tile('o', wordMultiplier = 5))
    assert(parser.parse(input) == expected)
  }

  test("should parse a string with both multipliers") {
    val input = "d o,5w g,2l,3w e,2l"
    val expected = List(
      Tile('d'),
      Tile('o', wordMultiplier = 5),
      Tile('g', letterMultiplier = 2, wordMultiplier = 3),
      Tile('e', letterMultiplier = 2)
    )
    assert(parser.parse(input) == expected)
  }
}
