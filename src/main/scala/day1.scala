import scala.collection.immutable.{HashSet, HashMap}

case class LetterCounts(twoCount: Int, threeCount: Int)
object dayTwo {
  def main(args: Array[String]): Unit = {
    val input: String = """long multi-line input here"""
    for (outer <- input.lines) {
      for (inner <- input.lines) {
        if (!outer.equals(inner)) {
          if (getWordDifference(outer, inner) <= 1) {
            println(outer, inner)
          }
        }
      }
    }
  }
  def getWordDifference(word1: String, word2: String): Int = {
    var positionDifferenceCount = 0
    for (i <- 0 until word1.size)
      if (word1(i) != word2(i)) {
        positionDifferenceCount += 1
      }
    return positionDifferenceCount
  }
  def getLetterFrequenciesForEachLine(
      inputLineIterator: Iterator[String],
      letterCounts: List[LetterCounts]): List[LetterCounts] = {
    // base case, when we have all line frequencies, calculate letter counts
    if (!inputLineIterator.hasNext) {
      return letterCounts
    }

    return getLetterFrequenciesForEachLine(
      inputLineIterator,
      List(
        getLetterCounts(getLetterFrequencies(inputLineIterator.next(),
                                             0,
                                             HashMap())))) ::: letterCounts
  }

  def getLetterCounts(letterFrequencies: HashMap[Char, Int]): LetterCounts = {
    val lettersOccurringTwice = letterFrequencies.filter {
      case (k, v) => v == 2
    }
    val lettersOccurringThreeTimes = letterFrequencies.filter {
      case (k, v) => v == 3
    }
    return LetterCounts(twoCount = Math.min(lettersOccurringTwice.size, 1),
                        threeCount =
                          Math.min(lettersOccurringThreeTimes.size, 1))
  }
  def getLetterFrequencies(
      letters: String,
      lettersIndex: Int,
      frequencies: HashMap[Char, Int]): HashMap[Char, Int] = {
    // base case
    if (lettersIndex == letters.length) {
      return frequencies
    }
    val letter = letters(lettersIndex)
    if (frequencies.contains(letter)) {
      return getLetterFrequencies(
        letters,
        lettersIndex + 1,
        frequencies + (letter -> (frequencies(letter) + 1)))
    } else {
      return getLetterFrequencies(letters,
                                  lettersIndex + 1,
                                  frequencies + (letter -> 1))
    }
  }
  def main1(args: Array[String]): Unit = {
    val input = """multi-line string of digits here"""
    val lineIterator: Iterator[String] = input.lines
    val letters = getLetterFrequenciesForEachLine(lineIterator, List())
    var twos = 0
    var threes = 0
    for (letterCounts <- letters) {
      twos += letterCounts.twoCount
      threes += letterCounts.threeCount
    }
    println(twos * threes)
  }
}

object dayOne {
  def getFrequency(input: String): Int = {
    var recordedFrequencies: HashSet[Int] = HashSet(0)
    var sum: Int = 0
    // some long loop that is sufficiently large to solve the problem
    for (i <- 1 to 100000) {
      for (line <- input.lines) {
        val operations = line(0)
        var offset: Int = line.replace("+", "").replace("-", "").toInt
        operations match {
          case '+' => {
            sum += offset
          }
          case '-' => {
            sum -= offset
          }
          case _ => throw new Exception("Default case should not be reached")
        }
        if (recordedFrequencies.contains(sum)) {
          return sum
        } else {
          recordedFrequencies = recordedFrequencies + sum
        }
      }
    }
    return -1
  }

  def mainDay1(args: Array[String]): Unit = {
    val input = """long multi-line string here"""
    println(getFrequency(input))
  }
}
