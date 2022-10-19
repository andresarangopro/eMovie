package com.old.domain.entities



import com.old.domain.model.Trailer

data class TrailerEntity(val name: String,
                         val site: String,
                         val key: String,
                         val type: String) {

    fun toTrailer() = Trailer(name,site,key, type )

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