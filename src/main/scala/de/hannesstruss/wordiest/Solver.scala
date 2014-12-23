package de.hannesstruss.wordiest

class Solver(dict: Dictionary) {
  def solveTwo(game: Word): (Word, Word) = {
    val permutations = permute(game)
    val permsWithScore: List[(Int, Word, Word)] = for {
      a <- permutations
      b <- permutations
      if a != b && !overlap(a, b)
    } yield (score(a) + score(b), a, b)

    val best = permsWithScore.reduce((left, right) => if (left._1 > right._1) left else right)
    (best._2, best._3)
  }

  def solve(word: Word): Word = {
    val permutations = permute(word)
    permutations.reduce((left, right) => if (score(left) > score(right)) left else right)
  }

  def pretty(word: Word): String = word.map(_.letter).mkString

  def overlap(a: Word, b: Word): Boolean = {
    (for {
      tileA <- a
      tileB <- b
      if tileA.eq(tileB)
    } yield true).nonEmpty
  }

  def score(word: Word): Int = word.map(_.value).sum * word.map(_.wordMultiplier).reduce(_ * _)

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
