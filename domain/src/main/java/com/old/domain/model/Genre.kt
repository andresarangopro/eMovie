package com.old.domain.model

import com.old.domain.empty

data class Genre(val id: Int, val name: String) {
    companion object {
        val empty = Genre(0, String.empty())
    }
}