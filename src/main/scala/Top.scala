package example

import chisel3._
import cde.Parameters
import diplomacy.LazyModule
import testchipip._
import rocketchip._

class ExampleTop(q: Parameters) extends BaseTop(q)
    with PeripheryBootROM with PeripheryCoreplexLocalInterrupter
    with PeripherySerial with PeripheryMasterMem {
  override lazy val module = Module(
    new ExampleTopModule(p, this, new ExampleTopBundle(p)))
}

class ExampleTopBundle(p: Parameters) extends BaseTopBundle(p)
  with PeripheryBootROMBundle with PeripheryCoreplexLocalInterrupterBundle
  with PeripheryMasterMemBundle with PeripherySerialBundle

class ExampleTopModule[+L <: ExampleTop, +B <: ExampleTopBundle](p: Parameters, l: L, b: => B)
  extends BaseTopModule(p, l, b)
  with PeripheryBootROMModule with PeripheryCoreplexLocalInterrupterModule
  with PeripheryMasterMemModule with PeripherySerialModule
  with HardwiredResetVector with DirectConnection with NoDebug

class ExampleTopWithPWM(q: Parameters) extends ExampleTop(q)
    with PeripheryPWM {
  override lazy val module = Module(
    new ExampleTopWithPWMModule(p, this, new ExampleTopWithPWMBundle(p)))
}

class ExampleTopWithPWMBundle(p: Parameters) extends ExampleTopBundle(p)
  with PeripheryPWMBundle

class ExampleTopWithPWMModule(p: Parameters, l: ExampleTopWithPWM, b: => ExampleTopWithPWMBundle)
  extends ExampleTopModule(p, l, b) with PeripheryPWMModule
