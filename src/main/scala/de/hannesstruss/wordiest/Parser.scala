package de.hannesstruss.wordiest

class Parser {
  val letterRx = """(\d+)l""".r
  val wordRx = """(\d+)w""".r

  def parse(input: String): Word = input.split(' ').toList.map(s => {
    val letter = s.charAt(0)

    Tile(
      letter,
      letterRx findFirstMatchIn s match {
        case Some(x) => x.group(1).toInt
        case None => 1
      },
      wordRx findFirstMatchIn s match {
        case Some(x) => x.group(1).toInt
        case None => 1
      }
    )
  })
}
