package com.ulternativetechnology.kotlintestproject

import org.junit.Test

class UnitTest {
    @Test
    fun `by lazy 테스트`() {
        class HeavyClass
        class MyClass {
            private val heavyObject: HeavyClass by lazy {
                println()
                println("Heavy Object init")
                HeavyClass()
            }

            fun accessObject() = println(heavyObject)
        }

        val someClass = MyClass()
        println("someClass init")
        someClass.accessObject()
        someClass.accessObject()
    }
}