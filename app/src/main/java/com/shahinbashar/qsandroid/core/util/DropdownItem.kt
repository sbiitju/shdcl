package com.shahinbashar.qsandroid.core.util

data class DropdownItem(val id: String, val name: String) {
    override fun toString(): String {
        return this.name
    }
}
