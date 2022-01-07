package com.sample.app.network

object APIConstants {

    object ClientConfig {
        const val KEY_AUTHORIZATION = "Authorization"
        const val KEY_BEARER = "Bearer "
        const val REQUEST_TIME_OUT = 1L
        const val MAX_IDEAL_CONNECTIONS = 5
        const val KEEP_ALIVE_DURATION = 20L

        const val DEFAULT_PAGE_SIZE = "20"
    }

    object CommonParams {
        const val KEY_OFFSET = "offset"
        const val KEY_ORDER = "order"
    }

    object MovieReviewValues {
        const val BY_PUBLICATION_DATE = "by-publication-date"
        const val BY_OPENING_DATE = "by-opening-date"

        const val ALL = "all"
        const val PICKS = "picks"
    }
}