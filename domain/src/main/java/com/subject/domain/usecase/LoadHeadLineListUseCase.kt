package com.subject.domain.usecase

import com.subject.domain.model.HeadLineResponse
import com.subject.domain.repository.HeadLineRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoadHeadLineListUseCase @Inject constructor(
    private val repository: HeadLineRepository,
) {
    suspend operator fun invoke(page: Int, pageSize: Int): Result<HeadLineResponse> =
        runCatching {
            return@runCatching repository.loadRemoteHeadLine(page, pageSize)
        }
}