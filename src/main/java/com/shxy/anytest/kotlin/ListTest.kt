package com.shxy.anytest.kotlin

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        var s = 1
        var a = "123 $s"
        println(a)
        // [1,1,1,2,2,3,3,3,4,4,5,5,5,5,5,5,6,6,6,7,7,8,9]
        // [<1,3>,<2,2>...]
        var list = listOf("123","456","8794","1")
        for (it in list){
            println(it.toString())
        }

        var threeList = list.filter { x -> x.equals("123") }
        println("*********")
        for (it in threeList){
            println(it)
        }
        println("*********")
        var map = mapOf<String,Int>("1" to 1,"2" to 2)
        for ((k,v) in map){
            println("k = $k,v = $v")
        }
    }

    fun max(a:Int,b:Int) = if (a>b) a else b

}
