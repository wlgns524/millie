package com.subject.domain.usecase

import com.subject.domain.model.HeadLine
import com.subject.domain.repository.HeadLineRepository
import javax.inject.Inject
import javax.inject.Singleton


//@Singleton
//class UpdateHeadLineUseCase @Inject constructor(
//    private val repository: HeadLineRepository,
//) {
//    suspend operator fun invoke(headLine: HeadLine) = repository.updateHeadLine(headLine)
//}