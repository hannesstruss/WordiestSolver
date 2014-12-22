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

  def isStartOfWord(beginning: List[Tile]): Boolean = {
    val str = beginning.map(_.letter).mkString
    val words = Set("ja", "jo")
    words.filter(_.startsWith(str)).nonEmpty
  }

  def isWord(tiles: List[Tile]): Boolean = {
    val str = tiles.map(_.letter).mkString
    val words = Set("ja", "jo")
    words contains str
  }

  def permute(word: List[Tile]): List[List[Tile]] = permute(word, List())

  def permute(tiles: List[Tile], prefix: List[Tile]): List[List[Tile]] = {
    if (!isStartOfWord(prefix)) {
      List()
    } else {
      val solutions = tiles match {
        case Nil => List()
        case List(_) => List(tiles)
        case _ =>
          (for (i <- tiles.indices.toList) yield {
            val elem = tiles(i)
            val newPrefix = prefix :+ elem

            if (isWord(newPrefix)) {
              println(newPrefix)
            }

            val (before, rest) = tiles.splitAt(i)
            val subpermutes = permute(before ++ rest.tail, newPrefix)
            subpermutes.map(elem :: _)
          }).flatten
      }
      solutions
    }
  }
}
