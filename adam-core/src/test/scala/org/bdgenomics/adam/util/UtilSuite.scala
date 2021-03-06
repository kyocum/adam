/**
 * Licensed to Big Data Genomics (BDG) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The BDG licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bdgenomics.adam.util

import org.bdgenomics.formats.avro.Contig

class UtilSuite extends SparkFunSuite {

  test("isSameConfig") {
    val a = Contig.newBuilder().setContigName("foo")
    val b = Contig.newBuilder().setContigName("bar")
    assert(!Util.isSameContig(a.build(), b.build()))
    b.setContigName("foo")
    assert(Util.isSameContig(a.build(), b.build()))

    // proper null handling
    assert(Util.isSameContig(null, null))
    assert(!Util.isSameContig(null, b.build()))
    assert(!Util.isSameContig(a.build(), null))

    a.setContigMD5("md5")
    // both md5s need to be set to change equality
    assert(!Util.isSameContig(a.build(), b.build()))
    b.setContigMD5("md5")
    assert(Util.isSameContig(a.build(), b.build()))
  }

}
