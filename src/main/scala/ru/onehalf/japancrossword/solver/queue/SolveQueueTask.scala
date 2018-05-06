package ru.onehalf.japancrossword.solver.queue

import com.typesafe.scalalogging.StrictLogging
import ru.onehalf.japancrossword.model.{Cell, Line}
import ru.onehalf.japancrossword.solver.LineSolver

/**
  * Задание для подбора строки
  *
  * @param metadata Данные по количестку закрашенных клеток в линии
  * @param line Подбираемая линия
  * @param solverType Решатель, которым еще не перебиралась данная строка
  * @param remainingCells Число ячеек, которые были не подобраны на момент создания задачи.
  *                       (Чтобы не гонять одного и того же решателя по одинаковым исходным данным)
  * @since 23.05.13 22:37
  * @author OneHalf
  */
case class SolveQueueTask(metadata: Array[Int], line: Line, solverType: LineSolver, remainingCells: Int) extends StrictLogging {

  def this(metadata: Array[Int], line: Line, solverType: LineSolver) =
    this(metadata: Array[Int], line: Line, solverType: LineSolver, line.toList.count(_ == Cell.NOT_KNOWN))


  override def equals(obj: Any): Boolean = {
    if (!obj.isInstanceOf[SolveQueueTask]) {
      return false
    }
    val o = obj.asInstanceOf[SolveQueueTask]
    if (!metadata.corresponds(o.metadata)((a,b) => a == b)) {
      println("metadata not equals")
      return false
    }
    if (line != o.line) {
      println("lines not equals")
      return false
    }
    true
  }

  override def toString: String = "Task[%s, line:%d:, NotKnownCount:%d, %s, solver:%s]"
    .format(metadata.mkString("[", ",", "]"), line.size, line.notKnownCount, line, solverType)
}
