package ml.combust.mleap.runtime.transformer.feature

import ml.combust.mleap.runtime.types._
import org.scalatest.FunSpec

class DCTSpec extends FunSpec {

  describe("#getFields") {
    it("has the correct inputs and outputs") {
      val transformer = new DCT("transformer", "input", "output", null)
      assert(transformer.getFields().get ==
        Seq(StructField("input", TensorType(DoubleType())),
          StructField("output", TensorType(DoubleType()))))
    }
  }
}