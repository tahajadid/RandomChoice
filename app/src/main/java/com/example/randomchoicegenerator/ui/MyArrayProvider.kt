package com.example.randomchoicegenerator

class myArrayProvider(val numbers : Int) {

    lateinit var myList : MutableList<String>

    fun getArrayList() : Array<String>{
        myList = mutableListOf("0")
        val tableNumbers = IntArray(numbers) { 1 * (it + 1) }

        tableNumbers.forEach { i->
            myList.add(i.toString())
        }

        return myList.toTypedArray()
    }

}