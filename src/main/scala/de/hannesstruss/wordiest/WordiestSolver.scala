package de.hannesstruss.wordiest

object WordiestSolver {
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

  case class Tile(letter: Char) {
    def value: Int = Values(letter)
  }

  type Word = List[Tile]

  def isStartOfWord(beginning: Word): Boolean = {
    val str = beginning.map(_.letter).mkString
    val words = Set("ja", "jo")
    words.filter(_.startsWith(str)).nonEmpty
  }

  def isWord(word: Word): Boolean = {
    val str = word.map(_.letter).mkString
    val words = Set("ja", "jo")
    words contains str
  }

  def permute(word: Word): List[Word] = permute(word, List())

  def permute(tiles: Word, prefix: Word): List[Word] = tiles match {
    case Nil => if (isWord(prefix)) List(prefix) else List(Nil)
    case _ =>
      val subsolutions: List[Word] = (for {
        i <- tiles.indices.toList
        elem = tiles(i)
        (before, after) = tiles.splitAt(i)
        tilesWithoutElem = before ++ after.tail
        newPrefix = prefix :+ elem
        if isStartOfWord(newPrefix)
      } yield permute(tilesWithoutElem, newPrefix)).flatten

      if (isWord(prefix)) {
        prefix :: subsolutions
      } else subsolutions
  }
}
