package com.part3.monoids

/**
  * Created by AMIYA on 6/24/2018.
  */
trait  Monoid[A] {
  def op(a1:A,a2:A) : A
  val zero : A
}