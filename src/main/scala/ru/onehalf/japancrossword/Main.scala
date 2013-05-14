package ru.onehalf.japancrossword

import solver.Orientation
import view.JapanCrosswordFrame
import model.{Metadata, JapanCrosswordModel}
import javax.swing.JFrame

/**
 * <p/>
 * <p/>
 * Created: 30.11.12 0:20
 * <p/>
 * @author OneHalf
 */
object Main {

  def parseParams(param: String): Array[Int] = (param split " ").map (_.toInt)

  def parseLine(orientation: Orientation.Orientation, string: String) : Metadata = {
    new Metadata(orientation, (string split ",\\s+").map(parseParams(_)))
  }

  def main(args: Array[String]) {

/*
    // Джек воробей
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "4, 2 3, 3 2 2, 4 2 4 4 2, 4 4 2 17 5, 6 2 28, 22 12 7 3 4, 27 11 5 14, 16 2 3 38, " +
      "15 2 2 1 2 34 1, 6 14 5 4 2 8 15, 5 3 1 7 4 2 3 2 1 12 4, 13 3 1 10 15 1 2 5 3 3, " +
      "3 16 4 1 19 11, 2 5 1 8 1 2 3 21 3 9, 8 1 6 4 2 20 8, 4 1 4 7 19 1 9, 3 4 4 3 32, " +
      "2 1 2 1 4 25 2, 2 1 1 4 3 14 5 3, 2 1 1 5 12 4 5, 1 1 1 8 8 1 6, 1 1 1 3 1 2 2 2 17, " +
      "1 2 1 6 5 1 18, 1 2 6 3 2 3 2 1 2 3 8, 1 1 1 6 2 4 2 3 2 3 8, 1 1 2 7 3 5 2 3 2 12 5, " +
      "1 2 3 4 2 6 5 7 2 4, 2 2 3 2 3 1 1 2 4 1 1 4, 2 1 3 2 5 3 1 2 3 2 3, 2 1 2 3 2 1 3 4 6 9, " +
      "4 3 2 1 1 4 1 4 8 8 2, 8 1 2 1 1 3 1 24 1 3, 3 4 1 2 1 1 4 1 29, 3 2 2 1 1 2 1 4 33, 4 3 2 1 1 2 5 2 33, " +
      "4 1 3 2 3 3 4 4 25 10 1, 9 2 3 3 5 26 1 10 3, 4 9 2 1 6 22 2 1 9 5, 4 8 4 4 19 1 12 3 6, " +
      "13 4 4 11 3 2 2 13 7, 15 1 17 1 12 4 2 1 10, 14 1 32 4 15, 13 12 26 6 7, 44 5 3 2 7, " +
      "24 17 6 4 6, 17 3 1 6 14 9 2, 17 2 1 2 3 16 6 3, 20 1 3 3 22 4, 18 2 24, 18 16 3 1, " +
      "3 28 2 3, 25 1 3, 8 15 2 3, 3 11 1 2 2, 1 11 4 2, 2 9 4 2, 6 3 1, 6 2 2 1, 1 4 2 1 1, " +
      "4 1 1 1 3, 1 5 1 4, 2 2 6, 2 1 1 3 2, 4 1 4, 5 4 3, 15, 5 3, 5 2, 7 2")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "22, 7 11, 5 10, 3 2 3 7, 4 1 2 6, 4 2 7 2 5, 5 2 4 6 2 4, 8 14, 3 3 11, 3 4 10, 3 2 8, " +
      "3 2 9, 3 4 16, 7 5 14, 7 11 11, 4 3 4 11 8, 5 4 13, 10 15, 5 7 2 5, 5 6 13, 3 1 7 4 6 , " +
      "3 2 6 18, 2 6 12, 2 3 22, 2 2 2 9 15, 3 4 3 4 2 5 9, 3 8 28, 2 8 8 5 1 12, 3 1 1 4 5 4 2 1 8 4, " +
      "3 2 5 2 5 2 2 10 4, 3 4 1 4 1 6 11 4, 3 3 4 1 2 7 8, 4 3 3 1 1 8 5, 6 2 2 10 3, 1 3 1 1 4 12 3, " +
      "1 2 3 1 1 2 10 5, 1 2 3 1 1 2 12 4, 2 1 1 2 1 2 5 13, 2 2 3 2 6 5 12, 1 3 3 6 6 5 6, " +
      "2 4 4 4 14 4, 2 4 5 3 4 6 7 4, 1 5 6 2 4 7 5 7, 2 5 7 2 2 5 13 7, " +
      "1 7 6 28 5, 1 7 11 2 9 8 2 1,  1 5 11 11 10 3 1, 7 8 2 2 23 1, 7 8 2 3 2 8 16, 8 9 1 2 1 8 16, " +
      "4 13 7 5 11, 4 3 10 8 5 14, 4 3 10 1 34, 2 2 3 13 16 16 2, 1 3 5 11 13 2 17 1, 9 10 1 2 7 23, " +
      "8 2 11 2 1 5 22 1, 9 2 11 2 6 5 10 3, 2 8 11 2 4 3 9 2 2 2, 2 4 4 6 2 1 4 4 11 2 5, " +
      "1 4 5 5 2 2 20 1 2 4, 1 9 1 3 3 3 16 4 2 4, 2 6 1 3 2 3 9 2 5 2 6, 2 6 1 2 1 2 1 8 9 2 5, " +
      "1 7 4 3 1 11 8 3 8, 7 3 10 15 11 4 4, 7 15 14 8 2 1 2, 7 8 6 9 6 1 2 3 1 2 1, 2 4 8 5 3 5 6 1 3 2 3 1, " +
      "2 4 6 2 4 2 5 6 2 3 2 3 1, 1 5 5 2 4 2 3 7 1 2 2 5, 6 5 2 4 1 4 2 4 1 2 2 1 1 1, 3 8 2 3 1 3 4 4 2 2 4 3, " +
      "3 4 5 2 2 3 3 2 1 7 1, 3 3 3 1 1 3 2 2 2 3 1 2 1")
*/


    // Земля
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "40 40, 34 34, 31 11 6 12, 28 28, 26 26, " +
      "24 2 24, 22 2 3 22, 20 2 3 20, 19 3 3 15 3, 18 5 2 18, " +
      "16 7 2 16, 4 10 6 1 6 8, 14 6 3 14, 13 10 13, 12 9 12, " +
      "5 5 9 11, 10 8 10, 17 10, 9 6 9, 8 1 2 1 1 8, " +
      "7 2 2 10 7, 7 2 2 12 7, 6 3 2 2 16 6, 9 2 3 18 6, 5 2 2 4 19 5, " +
      "11 4 21 5, 4 5 5 22 4, 10 4 24 4, 3 5 5 1 26 3, 3 4 6 4 26 3, " +
      "7 7 8 27 3, 2 3 6 2 37 2, 16 3 2 36 2, 15 2 2 7 28 2, 15 2 3 1 7 28 1, " +
      "1 1 9 3 4 2 7 28 1, 1 11 8 10 28 1, 1 8 8 9 29 1, 1 4 3 9 29 1, 1 3 8 29 1, " +
      "1 2 9 29, 1 1 11 29, 3 12 29, 4 12 4 34, 5 2 12 35, " +
      "2 9 13 30 1, 3 11 9 4 2 32 3, 12 10 2 2 36, 1 6 2 12 2 2 37, 1 6 1 13 4 36, " +
      "1 9 1 13 1 35 1, 1 10 17 34 1, 1 30 34 1, 1 31 37 1, 1 1 32 37 1, " +
      "1 1 1 3 1 24 36 2, 3 2 5 1 26 35 2, 2 1 3 1 30 25 2, 2 6 1 24 5 35 2, 12 24 5 34 3, " +
      "3 34 6 34 3, 3 34 6 35 3, 4 30 8 34 4, 4 33 8 34 4, 38 6 35 5, " +
      "5 32 14 28 5, 39 15 26 6, 6 49 25 6, 7 49 22 7, 7 51 16 8, " +
      "8 51 13 2 8, 9 51 12 2 9, 62 10 2 10, 10 61 3 10, 11 52 6 3 11, " +
      "12 35 13 7 2 8 3, 13 35 12 6 13, 7 6 34 10 5 14, 15 33 9 2 15, 16 41 16, " +
      "18 29 8 18, 19 28 5 19, 20 25 21, 4 17 23 22, 24 20 14 9, " +
      "26 18 26, 28 17 28, 31 18 31, 34 14 35, 40 40")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "42 42, 35 34, 31 5 2 31, 28 5 1 3 28, 11 14 8 2 4 19 6, " +
      "15 8 13 11 24, 22 8 6 11 22, 20 8 10 11 7 12, 19 4 4 9 12 19, 18 4 3 10 2 1 12 18, " +
      "16 2 3 10 2 2 15 16, 15 9 10 1 2 15 15, 14 8 11 2 15 14, 13 5 11 2 17 13, 12 7 11 19 12, " +
      "11 7 8 1 2 20 11, 10 7 5 8 23 10, 17 3 1 9 33, 9 7 4 9 24 9, 8 7 4 35 8, " +
      "7 8 37 7, 7 4 2 5 32 7, 6 4 2 5 34 6, 8 5 6 40, 5 1 1 7 33 5, " +
      "5 2 7 39, 4 3 3 33 4, 4 3 4 35 4, 3 4 4 35 3, 3 3 2 1 38 3, " +
      "3 7 47, 2 4 46 2, 2 4 47 2, 2 1 51, 1 52 1, " +
      "1 28 25 1, 1 27 25 1, 1 23 2 25 1, 1 22 24, 1 2 10 11 23, " +
      "13 3 10 28, 9 2 3 9 29, 10 2 3 38, 8 1 4 4 33, 8 1 3 3 32, " +
      "7 1 1 3 27 4, 6 4 2 3 2 18 3, 2 2 2 3 1 2 17 2, 15 10 4 2, 17 11 2 2, " +
      "1 17 13 3 3, 1 18 3 21 2, 1 22 29 1, 1 26 29 1, 1 42 16 1, " +
      "1 43 14 2, 2 44 13 2, 2 46 11 2, 2 48 10 2, 3 48 9 3, " +
      "3 49 7 3, 3 50 5 3, 4 1 50 3 2 4, 4 2 52 2 3 4, 5 1 55 3 5, " +
      "5 2 58 5, 6 1 58 6, 6 2 57 6, 7 1 56 7, 7 2 54 8, " +
      "2 5 2 54 8, 9 1 51 9, 10 49 10, 10 48 10, 11 46 11, " +
      "12 40 2 12, 13 31 3 13, 2 11 26 3 14, 15 26 3 15, 16 26 216, " +
      "18 26 1 12 5, 11 7 25 19, 20 22 21, 22 20 22, 24 17 24, " +
      "26 16 26, 8 19 14 13 14, 31 9 31, 34 2 34, 40 40")


    /*
        // Собачка в профиль
        val horizonLine = parseLine(Orientation.HORIZONTAL,
          "7, 4 2, 3 1 1, 1 1 1, 3 1 2, 1 1 1, 1 1 1, 1 2 1, 1 1 1, 3 1 1, 1 1 1 1 1, 1 1 1, 1 3 1 2, " +
          "1 4, 3 2, 11 2, 12 2, 11 2, 11 2, 9 2")

        val verticalLine = parseLine(Orientation.VERTICAL,
          "2 6, 3 1 5, 7 1 1 6, 2 1 1 2 1 5, 1 1 2 1 5, 1 2 5, 1 5, 1 5, 2 2 5, 1 2 2 5, 1 3 5, 2 5 3, 5 1, 8, 8")
    */

/*
    // Пиратский флаг
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "2, 10, 13, 5 1, 1 2, 12, 12, 12, 6 1 1, 7 1 2, 2 2 4, 1 1 1 4, 1 1 1 4, 1 1 2 3, " +
      "2 3 3, 7 1 2, 7 1 1, 12, 3 6, 2 2")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "3 2, 2 5 4, 2 14, 2 6 4, 2 5 3, 2 5 1 1 3, 2 5 1 3, 2 3 2 5, 2 3 6 2, 2 5 2 2, 2 3 3 5, " +
      "2 4 6 3, 2 12 2, 2 1 7, 2")
*/

/*
    // Собачка
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "2, 4, 4 2 3, 1 3 1 2, 1 2 2 2, 2 2 2 5, 2 1 2 7, 2 4 5, 2 3, 3 1 2 3, " +
      "1 7 2, 1 4 1 2, 1 4 1 2, 1 7 2, 4 1 2 3, 2 3, 2 2 5 4, 1 2 2 7, 1 3 5, " +
      "1 1 2 2, 2 1 2 2, 4 1 3, 1 2, 4, 2")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "4, 1 2 5, 1 2 2 2, 2 1 2 2 1 1, 1 2 2 1 3 1, " +
      "2 1 1 1 4, 2 6 2, 2 2, 2 1, 1 1 1 1, 1 4 1, 1 6 1, 1 6 1, 1 4 1, " +
      "3 1 1 1 1 3, 2 4 1 1 4 2, 1 3 4 3 1, 3 5 5 4, 25, 4 10 4")
*/

/*
    // Девочка
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "1 3 3 2, 1 6 1 2 1 1, 1 8 3 6 1 2, 1 6 1 2 1 1 2 3, 1 1 1 3 3 2 2 1, " +
      "1 1 4 1 1 1 1 2 1 3, 8 5 3 2, 6 8 1, 1 1 1 5 1 1 2 1 2, 1 5 3 2 1, 2 8 1 2 1 2, " +
      "13 3 4, 3 5 6 3 1 2, 5 5 7 4 2, 4 2 5 6 5, 3 1 1 4 5 4 1, 3 1 15 2 1 2, 4 2 17 3 3, " +
      "5 1 17 1 3 3, 9 10 4 1 1 5, 7 15 2 1 1 4, 7 17 2 1 6, 5 10 3 8 2, 5 4 4 3 4 4, 3 4 4 3 3 4 1, " +
      "5 4 6 6, 5 2 1 5 5, 6 1 2 4 3, 7 2 3, 2 7")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "8, 10, 10, 2 2 3 7, 1 1 1 7, 1 6, 1 6, 1 2 3, 1 2 1, 3 1 4, 3 3 7, 3 14, 1 5 16, 3 3 11 5, 9 4 7 4, " +
      "3 1 4 7 4, 6 4 9 3, 5 1 1 12 2, 6 1 17 1, 1 14 7, 11 3 2 1, 7 3 4 5, 6 3 5 3, 3 5 4 2 4 1, " +
      "2 2 2 6 2 6, 1 3 1 6 3 3 5, 1 1 1 5 4 7, 2 2 3 12, 4 3 2 1 5, 1 4 3 1 4 1, 1 1 3 2 2 2, 2 1 3 1 2 1, " +
      "2 4 1 1 2 1, 1 3 3 1 1 1 3, 1 1 7 1 5, 1 1 1 1 1 1 1 4, 4 1 1 3 4, 1 2 2 5 3, 2 5 6 3, 1 1 3 2 3")
*/

/*
    // Инкогнито по венециански
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "5, 4 2 4, 4 1 5 3, 3 3 1 6 3, 2 2 1 2 7 3, 2 5 1 2 7 4, 1 9 3 14 3, 2 9 5 20 1, 3 8 7 8 11 2, " +
      "3 5 3 2 10 2 4, 5 6 4 2 5 6 1, 6 6 3 2 1 1 10, 5 6 1 3 2 10 1, 4 7 4 2 5 4, 3 9 2 2 4 5 2, " +
      "1 10 2 1 1 2 5 1, 1 12 2 2 1 4 4 1, 1 13 1 4 1 6 3 2, 1 13 1 4 1 8 3, 1 13 4 2 1 10 1, " +
      "1 14 3 1 3 11, 1 14 2 1 4 2 5 2, 2 14 1 9 1 1 4, 1 14 15 6, 1 17 9 7 5, 1 29 10 4, 1 15 9 13 4, " +
      "1 15 12 4 3, 1 17 11 6 2, 1 23 20 1, 2 16 5 15, 1 17 8 12, 1 13 7 9 5 2, 1 12 4 10 1 3, 2 11 7 7 6, " +
      "1 11 9 4 4, 1 9 11 5 4, 2 8 5 3 4, 1 7 7 5, 2 6 4 6, 2 6 2 9, 2 4 10, 2 2 8, 1 1 8, 3 7")

    val verticalLine = parseLine(Orientation.VERTICAL,
      "9, 2 4 5, 4 3 5 4, 2 1 2 13 4, 2 2 3 19 3, 1 4 4 22 2, 2 4 3 25 3, 1 5 4 27 1, 2 5 3 28 1, 3 7 30, " +
      "1 1 6 28, 1 8 28, 1 3 27, 2 27, 32, 29, 26, 8 2 5, 5 4 3 5, 1 5 2 1 2, 4 2 4 3 1 2, 5 8 3 2 2, " +
      "6 6 3 2 3, 1 1 1 3 3 2 3, 2 1 3 2 2, 2 2 4 4 2, 2 1 4 2 2, 3 2 3 2 3, 3 3 3 2 3, 2 4 3 2 2, " +
      "1 7 3 3 2, 4 9 3 3 3 2, 5 3 4 1 2 3 2, 5 5 1 2 2 1, 6 5 1 3 2 2, 5 1 2 4 2 3 2 2, " +
      "5 8 2 2 4 2 3, 4 9 5 4 2 4, 5 4 6 4 3 2 4, 6 4 6 5 3 3 3, 7 3 4 6 3 3 2 1, 7 3 1 4 10 4 2, " +
      "6 3 3 4 11 4 4, 7 4 3 3 3 5 2 5, 6 4 4 5 2 5 1 1 6, 1 3 1 2 4 3 2 5 1 7, 2 2 3 2 1 1 5 4 11, " +
      "7 1 1 1 1 1 6 2 12, 7 2 2 2 8 1 13, 6 3 8 9 13")
*/

/*
    // Домик
    val horizonLine = parseLine(Orientation.HORIZONTAL, "1 1, 8, 4 3, 3 1 3, 3 6, 3 1, 3 1, 4, 8, 1 1")
    val verticalLine = parseLine(Orientation.VERTICAL, "2, 4, 6, 3 3, 3 3, 8, 1 1 1, 1 1 1, 4 1, 4 1, 5 2")
*/


/*    // Бабочка
    val horizonLine = parseLine(Orientation.HORIZONTAL,
      "3 7, 5 2 4, 7 1 5 4, 3 3 1 1 2 1 2 4, 2 4 3 3 1 1 1 1 3, 5 11 1 2 2 3, 2 1 1 5 3 1 5 3, " +
      "2 2 1 3 1 1 1 3 5, 2 2 13 4, 2 4 1 3 2 4, 9 8, 3 1 1 1 1 1 2 8, 15 11, 3 1 3 1 1 2 8, " +
      "4 4 5 2, 2 3 1 3 1 5, 2 2 5 5 1 3, 2 1 2 5 2 4 3, 2 1 1 4 5 8, 5 6 3 1 1 2 1 2, 2 1 1 3 3 5 2 4, " +
      "4 4 1 1 1 1 1 4, 7 1 2 2 4, 1 3 2 1 4, 3 5")

    val verticalLine = parseLine(Orientation.VERTICAL, "1, 4 3 4, 6 3 6, 2 1 2 3 2 1 2, 7 2 1 2 7, 3 2 2 5 1 1 2 1, 3 1 1 3 1 1 3 1 3, " +
      "3 2 1 7 1 1 4, 3 2 3 2 2 2 3, 21, 6 1 1 1 6, 17, 1 2 1 1 1 1 2 1 1, 2 3 3 1 2 1, 4 3 1 3 4, " +
      "1 8 8 1, 1 1 2 1 5 4 1 1, 3 1 4 1 3 1 1 2, 1 1 1 1 7 2 2 1 1, 1 2 1 2 4 1 2 2 2, 1 1 1 15 1 1, 1 2 3 7 2 1 3, " +
      "2 4 2 3 2 2 1 2, 3 4 3 6 2, 9 3 3 4, 3 1 2 3 3 2, 3 2 1 1 3, 5 3, 3 2, 1 1")*/


    val CELL_SIZE = 15
    val FONT_SIZE = 9
    new JapanCrosswordFrame(new JapanCrosswordModel(horizonLine, verticalLine), CELL_SIZE, FONT_SIZE).setVisible(true)
  }
}
