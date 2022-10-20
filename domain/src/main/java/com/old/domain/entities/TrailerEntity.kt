package com.old.domain.entities



import com.old.domain.model.Trailer

data class TrailerEntity(val name: String,
                         val site: String,
                         val key: String,
                         val type: String) {

    data class TrailerResultsEntity(val results: List<TrailerEntity>){

        fun toTrailerList(): List<Trailer> = results.map {
            it.run{
                Trailer(
                    name,
                    site,
                    key,
                    type
                )
            }
        }.filter { item -> item.type == "Trailer" }
    }

}