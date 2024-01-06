package com.shdclgroup.app.core.util

data class DropdownItem(val id: String, val name: String) {
    override fun toString(): String {
        return this.name
    }
}
