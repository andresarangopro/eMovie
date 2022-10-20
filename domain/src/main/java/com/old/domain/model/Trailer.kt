package com.old.domain.model

import com.old.domain.empty

class Trailer(
    val name: String,
    val site: String,
    val key: String,
    val type: String
){
     companion object {
        val empty = Trailer(
             String.empty(), String.empty(), String.empty(),
            String.empty()
        )
    }

}