package de.hannesstruss

import scala.io.Source

package object wordiest {
  val Values = Map(
    'a' -> 1,
    'b' -> 2,
    'c' -> 3,
    'd' -> 2,
    'e' -> 1,
    'f' -> 4,
    'g' -> 3,
    'h' -> 3,
    'i' -> 1,
    'j' -> 10,
    'k' -> 5,
    'l' -> 2,
    'm' -> 4,
    'n' -> 2,
    'o' -> 1,
    'p' -> 3,
    'q' -> 10,
    'r' -> 1,
    's' -> 1,
    't' -> 1,
    'u' -> 2,
    'v' -> 6,
    'w' -> 4,
    'x' -> 8,
    'y' -> 4,
    'z' -> 10
  )

  case class Tile(letter: Char, letterMultiplier: Int = 1, wordMultiplier: Int = 1) {
    def value: Int = Values(letter) * letterMultiplier
  }

  type Word = List[Tile]

  def loadWordsFromFile(): Set[String] = Source.fromURL(getClass.getResource("/dictionary.txt")).getLines().toSet

  def parse(s: String): Word = {
    new Parser().parse(s)
  }
}
