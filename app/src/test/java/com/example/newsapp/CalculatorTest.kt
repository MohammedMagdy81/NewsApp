package com.example.newsapp

import org.junit.Assert.*
import org.junit.Test
import java.lang.NullPointerException

class CalculatorTest{

    @Test
    fun add_when_negative_input_negative_output(){

        val calculator=Calculator()

        //arrange // define inputs
        var n1=-10
        var n2=-30

        //act //call function and store result
        val result= calculator.add(n1,n2)
        //assert
        assertEquals(-40,result)
    }

    @Test(expected = Exception::class)
    fun divide_when_zero_input(){
        val calculator=Calculator()

        var n1= 30
        var n2=0

        var res= calculator.divide(n1, n2)



    }
}