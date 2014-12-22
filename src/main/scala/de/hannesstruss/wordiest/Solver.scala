package de.hannesstruss.wordiest

class Solver(dict: Dictionary) {
  def permute(word: Word): List[Word] = permute(word, List())

  def permute(tiles: Word, prefix: Word): List[Word] = tiles match {
    case Nil => if (dict.isWord(prefix)) List(prefix) else List(Nil)
    case _ =>
      val subsolutions: List[Word] = (for {
        i <- tiles.indices.toList
        elem = tiles(i)
        (before, after) = tiles.splitAt(i)
        tilesWithoutElem = before ++ after.tail
        newPrefix = prefix :+ elem
        if dict.isBeginningOfWord(newPrefix)
      } yield permute(tilesWithoutElem, newPrefix)).flatten

      if (dict.isWord(prefix)) prefix :: subsolutions
      else subsolutions
  }
}
