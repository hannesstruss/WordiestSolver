package de.hannesstruss.wordiest

class RealDictionary(words: Set[String]) extends Dictionary {
  val beginnings: Set[String] = for {
    word <- words
    sublen <- 1 to word.length
  } yield word.take(sublen)

  override def isBeginningOfWord(beginning: Word): Boolean = beginning match {
    case Nil => true
    case _ => beginnings contains wordToString(beginning)
  }

  override def isWord(word: Word): Boolean =
    words contains wordToString(word)
}
