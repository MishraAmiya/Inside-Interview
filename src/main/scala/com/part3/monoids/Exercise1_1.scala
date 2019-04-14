package com.part3.monoids

/**
  * Created by AMIYA on 6/24/2018.
  */

class Exercise1_1 {
  val iniAddition =  new Monoid[Int] {
    override def op(a1: Int, a2: Int): Int = {a1 + a2}

    override val zero: Int = 0
  }

  val intMultiplication = new Monoid[Int] {
    override def op(a1: Int, a2: Int): Int = {a1 * a2}

    override val zero: Int = 1
  }

  val booleanOr = new Monoid[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2

    override val zero: Boolean = false
  }

  val booleanAnd = new Monoid[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2

    override val zero: Boolean = true
  }
}

