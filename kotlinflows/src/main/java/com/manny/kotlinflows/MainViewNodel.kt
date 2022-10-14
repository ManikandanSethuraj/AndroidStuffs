package com.manny.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * A Flow is a Coroutine which can emit multiple values over a course of time.
 */


class MainViewNodel : ViewModel() {

    val countDownFlow = flow<Int>{
        val startingValue = 10
        var otherValue = startingValue
        emit(otherValue)
        while (otherValue > 0){
            delay(1000L)
            otherValue--
            emit(otherValue)
        }
    }

    val flowOne = flow<Int> {
        emit(1)
        delay(500L)
        emit(2)
    }

    init {
            // collectFlows()
            // collectFlowsDummy()
          //  countReduceFold()
         //   flatMapFlow()
            multipleFlow()
        }

    private fun multipleFlow(){

        val flowTwo = flow<String> {
            emit("This is also")
            delay(1000L)
            emit("This is String")
            delay(1000L)
            emit("Shhhh")
            delay(1000L)
        }

        viewModelScope.launch {
            flowTwo.onEach {
                println("This is Combined $it")
            }.buffer()
                .collect{
                println("Show All $it")
            }
        }

    }

    private fun flatMapFlow(){
    viewModelScope.launch {
        flowOne.flatMapConcat {
            flow {
                emit(it + 1)
                delay(500)
                emit(it + 2)
            }
        }.collect{
            println("The Value of Flat map $it")
        }
    }
}

    private fun collectFlows(){
        viewModelScope.launch {
            countDownFlow.collectLatest {
                value ->
                delay(2000)
                println("Latest Value $value")
            }
            countDownFlow.collect{
                value ->
                delay(1000)
                println("Every Value $value")
            }

            countDownFlow.filter {
                it % 2 == 0
            }.map {
                it * it
            }.onEach {
                print("OnEach Print $it")
            }.collect{
                println("Filter Value $it")
            }

        }
    }

    private fun collectFlowsDummy(){
        countDownFlow.onEach {
            val k = it/2
            println("divided Value $k")
        }.launchIn(viewModelScope)
    }

    private fun countReduceFold() {
        viewModelScope.launch {
            val count = countDownFlow.count {
                it % 2 == 0
            }
            println("The Count value is $count")
        }

        viewModelScope.launch {
            val reduce = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            println("The Reduce Value is $reduce")
        }

        viewModelScope.launch {
            val fold = countDownFlow.fold(200) { accumulator, value ->
                accumulator + value
            }
            println("The Fold Value is $fold")
        }
    }

}

