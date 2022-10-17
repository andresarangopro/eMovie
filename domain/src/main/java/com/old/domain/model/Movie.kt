package com.old.domain.model

import com.old.domain.empty

data class Movie(val id: Int, val poster: String) {
    companion object {
        val empty = Movie(0, String.empty())
    }
}
