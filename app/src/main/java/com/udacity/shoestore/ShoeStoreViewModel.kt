package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.util.Event

class ShoeStoreViewModel: ViewModel() {

    private var isOnboarded = false

    private val shoesArrayList = arrayListOf<Shoe>()

    var _shoe = Shoe(
        "Gazelle",
        10.5,
        "Adidas",
        "The Gazelle OG features the original outsole and soft pigskin suede for the perfect vintage look and feel"
    )

    fun shoe(): Shoe {
        return _shoe
    }

    val _shoes = MutableLiveData<ArrayList<Shoe>>(shoesArrayList)
    val shoes: LiveData<ArrayList<Shoe>>
        get() = _shoes

    private val _newDestination = MutableLiveData<Event<Int>>()

    val newDestination: LiveData<Event<Int>>
        get() = _newDestination

    fun setNewDestination(destinationId: Int) {
        _newDestination.value = Event(destinationId)
    }

    fun isOnboarded(): Boolean {
        return isOnboarded
    }

    fun onBoard(destinationId: Int) {
        isOnboarded = true
        _newDestination.value = Event(destinationId)
    }

    fun addShoe(shoe: Shoe, destinationId: Int) {
        shoesArrayList.add(shoe)

        _shoes.value = shoesArrayList
        _newDestination.value = Event(destinationId)
    }
}