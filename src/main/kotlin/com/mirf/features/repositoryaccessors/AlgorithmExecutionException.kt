package com.mirf.features.repositoryaccessors

class AlgorithmExecutionException : RuntimeException {
    constructor() {}

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace) {
    }
}
