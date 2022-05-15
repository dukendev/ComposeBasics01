package com.ysanjeet535.composelazycolumnwithexpandablecards

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

     fun getDummyList() : List<Item>{
        val list = mutableListOf<Item>()
        for(i in 1..50){

            list.add(Item(
                name = "Person $i",
                age = i,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque metus felis, convallis ac vehicula nec, fermentum nec augue. Cras neque urna, imperdiet id erat et, sollicitudin tempus mauris. Phasellus condimentum fermentum massa vel laoreet. Donec commodo odio ligula, vitae laoreet nibh ornare ac. Donec fermentum nunc egestas sodales tincidunt. Vivamus efficitur erat placerat, malesuada diam quis, dictum velit. Praesent mattis nunc ut neque viverra ultrices."
            ))
        }
        return list
    }
}

data class Item(
    val name: String,
    val age: Int,
    val description: String
)