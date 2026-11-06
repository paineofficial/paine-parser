package com.paine.parser.service

import org.springframework.stereotype.Service

import org.apache.logging.log4j.LogManager

import org.jsoup.Jsoup

@Service
class WebsiteParserService {

    private val log = LogManager.getLogger(
        WebsiteParserService::class.java
    )

    fun parsePage(url: String): PageReport {
        log.info("Start parsing page: {}", url)

        val document = Jsoup.connect(url)
            .userAgent("Mozilla/5.0")
            .timeout(10_000)
            .get()

        val title = document.selectFirst("h1")
            ?.text()
            .orEmpty()

        val description = document.selectFirst(
            ".field-name-body, .field--name-body, main, #content"
        )?.text().orEmpty()

        log.info("Page parsed successfully: {}", title)

        return PageReport(
            title = title,
            url = url,
            description = description
        )
    }
}

data class PageReport(
    val title: String,
    val url: String,
    val description: String
)
