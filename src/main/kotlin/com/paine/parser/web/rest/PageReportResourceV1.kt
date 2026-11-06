package com.paine.parser.web.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping

import com.paine.parser.service.WebsiteParserService
import com.paine.parser.service.PageReport

import org.apache.logging.log4j.LogManager

@RestController
@RequestMapping("/api/v1/page-report")
class PageReportResourceV1(
    private val parserService: WebsiteParserService
) {

    private val log = LogManager.getLogger(
        WebsiteParserService::class.java
    )

    @GetMapping("/data")
    fun getPageReport(
        @RequestParam url: String
    ): PageReport {
        val result = parserService.parsePage(url)
        log.info("$url: $result")
        return result
    }
}
