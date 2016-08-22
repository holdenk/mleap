package com.truecar.mleap.runtime.types

/**
 * Created by hwilkins on 10/23/15.
 */
sealed trait DataType extends Serializable {
  def fits(other: DataType): Boolean = this == other
}
sealed trait BasicType extends Serializable

object LongType extends DataType with BasicType
object BooleanType extends DataType with BasicType
object DoubleType extends DataType with BasicType
object StringType extends DataType with BasicType
case class TensorType(base: BasicType, dimensions: Seq[Int]) extends DataType {
  override def fits(other: DataType): Boolean = {
    if(super.fits(other)) { return true }

    other match {
      case TensorType(ob, od) => base == ob && dimFit(od)
      case _ => false
    }
  }

  private def dimFit(d2: Seq[Int]): Boolean = {
    if(dimensions.length == d2.length) {
      for((dd1, dd2) <- dimensions.zip(d2)) {
        if(dd1 != -1 && dd1 != dd2) { return false }
      }
      true
    } else { false }
  }
}
case class ListType(base: BasicType) extends DataType

object TensorType {
  def doubleVector(dim: Int = -1): TensorType = TensorType(DoubleType, Seq(dim))
}