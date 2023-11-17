package com.subject.data.model.exception

class RedirectionException(
    override val statusCode: Int,
    override val message: String,
) : DataException(statusCode, message)