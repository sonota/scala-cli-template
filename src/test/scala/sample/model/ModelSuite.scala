package sample.model

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class ModelSuite extends FunSuite with BeforeAndAfter {

  before {
    // ...
  }

  after {
    // ...
  }

  test("Model.add: 基本ケース"){
    assert(Model.add(1, 2) == 3)
  }

}
