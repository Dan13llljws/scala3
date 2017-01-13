package dotty.tools.dottydoc.api.scala

import dotty.tools.dottydoc.DocDriver
import dotty.tools.dottydoc.model.Package

import scala.collection.Map
import java.net.URL

/** The Dottydoc API is fairly simple. The tool creates an index by calling:
  * `createIndex` with the same argument list as you would the compiler - e.g:
  *
  * ```scala
  * val array: Array[String] = Array(
  *   "-language:Scala2"
  * )
  *
  * val index: Map[String, Package] = createIndex(array)
  * ```
  *
  * Once the index has been generated, the tool can also build a documentation
  * API given a Mustache template and a flat resources structure (i.e. absolute
  * paths to each resource, which will be put in the same directory).
  *
  * ```scala
  * buildDocs("path/to/output/dir", templateURL, resources, index)
  * ```
  *
  * The tool can also generate JSON from the created index using `indexToJson`
  * or directly using `createJsonIndex`
  */
trait Dottydoc extends DocDriver {
  /** Creates index from compiler arguments */
  def createIndex(args: Array[String]): Map[String, Package] =
    compiledDocs(args)

  /** Creates JSON from compiler arguments */
  def createJsonIndex(args: Array[String]): String =
    indexToJson(compiledDocs(args))
}
