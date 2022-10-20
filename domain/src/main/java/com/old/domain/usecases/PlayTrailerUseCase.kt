package com.old.domain.usecases

import android.content.Context
import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.old.domainmodel.UseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PlayTrailerUseCase
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val navigator: INavigator
) : UseCase<UseCase.None, PlayTrailerUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Either.Right(None())
    }

    data class Params(val url: String)
}
