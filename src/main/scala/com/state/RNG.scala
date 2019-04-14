package com.state

/**
  * Created by AMIYA on 4/15/2018.
  */

trait RaNuGe{
  def nextInt():(Int,RaNuGe)
}
object RNG {

  def main(args: Array[String]): Unit = {
//    val out:(Int,RaNuGe)= nonNegativeInt(SimpleRNG(10))
    val (list,rng) = ints(1)(SimpleRNG(10))
    list.map(v => println(v))
  }

  def nonNegativeInt(rng:RaNuGe) : (Int, RaNuGe) ={
    val (i,r)=rng.nextInt()
    (if(i < 0) -(i + 1) else i,r)
  }

  def ints(count: Int)(rng:RaNuGe) : (List[Int],RaNuGe) ={

    def generateRandomInts(count: Int,x:List[Int])(rng:RaNuGe) : (List[Int],RaNuGe) = {
      if (count < 0) {
        (x, rng)
      } else {
        val (n, r) = SimpleRNG(count).nextInt
        generateRandomInts(count - 1,n +: x)(rng)
      }
    }
    generateRandomInts(count,List())(rng)
  }
}
case class SimpleRNG(seed: Long) extends RaNuGe {
  def nextInt: (Int, RaNuGe) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
    val nextRNG = SimpleRNG(newSeed) // The next state, which is an `RNG` instance created from the new seed.
    val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
    (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
  }
}



