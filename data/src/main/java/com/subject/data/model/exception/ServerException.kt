package com.subject.data.model.exception

class ServerException(
    override val statusCode: Int,
    override val message: String,
) : DataException(statusCode, message)
