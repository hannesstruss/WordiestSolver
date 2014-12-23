package de.hannesstruss.wordiest

import scala.io.StdIn

class SolverCLI() {
  def run() = {
    println("Loading dictionary...")

    val dictWords = loadWordsFromFile()
    val solver = new Solver(new RealDictionary(dictWords))
    val parser = new Parser

    println("Hello!")

    var running = true
    while (running) {
      println("Enter some letters:")
      print("> ")
      val line = StdIn.readLine()

      if (line == null || line.length == 0) {
        running = false
      } else {
        val game = parser.parse(line)
        print("Solving ")
        println(game)

        val (one, two) = solver.solveTwo(game)

        println(one)
        println(two)

        println(s"${solver.score(one)} + ${solver.score(two)} = ${solver.score(one) + solver.score(two)}")
      }
    }
  }
}
