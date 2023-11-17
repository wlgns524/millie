package com.subject.domain.usecase

import com.subject.domain.model.HeadLine
import com.subject.domain.repository.HeadLineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetHeadLineListUseCase @Inject constructor(
    private val repository: HeadLineRepository,
) {
    suspend operator fun invoke(): Flow<List<HeadLine>> = repository.getHeadLineList()
}