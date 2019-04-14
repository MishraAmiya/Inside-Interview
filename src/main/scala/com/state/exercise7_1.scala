package com.state

import java.util.concurrent.{ExecutorService, Future}

/**
  * Created by AMIYA on 5/27/2018.
  */
object exercise7_1 {

  def sum(ints: IndexedSeq[Int]): Int ={
    if(ints.length <= 1)  ints.headOption.getOrElse(0)
    else{
      val(l,r) = ints.splitAt(ints.length/2)
      sum(l) + sum(r)
    }
  }
}

object Par{
  type Par[A] = ExecutorService => Future[A]

//  def unit[A](a: => A):Par[A] = {}
}

