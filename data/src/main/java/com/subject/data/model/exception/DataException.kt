package com.subject.data.model.exception

import java.io.IOException

open class DataException(
    open val statusCode: Int,
    override val message: String,
) : IOException(message)
