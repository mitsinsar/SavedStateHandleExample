package com.mitsinsar.statehandling

object DummyUserProvider {

    fun createDummyUser(): User {
        return User("Sinan", isActive = true)
    }
}
