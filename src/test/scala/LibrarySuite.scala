/*
 * This Scala Testsuite was auto generated by running 'gradle init --type scala-library'
 * by 'AMIYA' at '4/15/18 2:25 PM' with Gradle 3.2.1
 *
 * @author AMIYA, @date 4/15/18 2:25 PM
 */

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {
  test("someLibraryMethod is always true") {
    def library = new Library()
    assert(library.someLibraryMethod)
  }
}
