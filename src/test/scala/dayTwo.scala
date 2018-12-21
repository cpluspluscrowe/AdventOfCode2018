import org.scalatest._
import scala.collection.immutable.HashMap

class StackSpec extends FlatSpec {
  "getLetterCounts" should "return correct frequencies for a single letter" in {
    val frequencies = dayTwo.getLetterFrequencies("a", 0, HashMap())
    assert(frequencies.contains('a'))
    assert(frequencies('a') == 1)
  }
  "getLetterCounts" should "return correct frequencies for multiple letters" in {
    val frequencies = dayTwo.getLetterFrequencies("aba", 0, HashMap())
    assert(frequencies.contains('b'))
    assert(frequencies('a') == 2)
    assert(frequencies('b') == 1)
  }
  "getLetterFrequencies" should "return correct three counts" in {
    val frequencies = HashMap('a' -> 3)
    val counts = dayTwo.getLetterCounts(frequencies)
    assert(counts.threeCount == 1)
    assert(counts.twoCount == 0)
  }
  "getLetterFrequencies" should "return correct two counts" in {
    val frequencies = HashMap('a' -> 2)
    val counts = dayTwo.getLetterCounts(frequencies)
    assert(counts.threeCount == 0)
    assert(counts.twoCount == 1)
  }
  "getLetterFrequencies" should "return correct counts" in {
    val frequencies = HashMap('a' -> 2, 'b' -> 2, 'c' -> 3)
    val counts = dayTwo.getLetterCounts(frequencies)
    assert(counts.threeCount == 1)
    assert(counts.twoCount == 2)
  }
  "combining both count methods" should "return correct counts" in {
    val counts =
      dayTwo.getLetterCounts(dayTwo.getLetterFrequencies("a", 0, HashMap()))
    assert(counts.threeCount == 0)
    assert(counts.twoCount == 0)

    val counts2 =
      dayTwo.getLetterCounts(dayTwo.getLetterFrequencies("aba", 0, HashMap()))
    assert(counts2.threeCount == 0)
    assert(counts2.twoCount == 1)

    val counts3 =
      dayTwo.getLetterCounts(dayTwo.getLetterFrequencies("aaabb", 0, HashMap()))
    assert(counts3.threeCount == 1)
    assert(counts3.twoCount == 1)

    val counts4 = dayTwo.getLetterCounts(
      dayTwo.getLetterFrequencies("aaabbbcccdd", 0, HashMap()))
    assert(counts4.threeCount == 3)
    assert(counts4.twoCount == 1)
  }
  "getLetterFrequenciesForEachLine" should "return correct frequencies for single-line inputs" in {
    val input = """aabbb"""
    val counts = dayTwo.getLetterFrequenciesForEachLine(input.lines, HashMap())
    assert(counts.twoCount == 1)
    assert(counts.threeCount == 1)
  }
}
