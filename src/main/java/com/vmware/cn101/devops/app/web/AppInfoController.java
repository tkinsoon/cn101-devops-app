package com.vmware.cn101.devops.app.web;

import com.vmware.cn101.devops.app.model.AppInfo;
import com.vmware.cn101.devops.app.service.AppInfoService;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppInfoController implements Controller {

    private final Logger logger = LoggerFactory.getLogger(AppInfoController.class);
    private AppInfoService appInfoService;
    private static final Counter requests =
            Counter.build().name("requests_total").help("Total requests.").register();
    private static final Summary receivedBytes = Summary.build()
            .name("requests_size_bytes").help("Request size in bytes.").register();
    private static final Summary requestLatency = Summary.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();

    public AppInfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAppInfo(HttpServletRequest request) throws Exception {
        Summary.Timer requestTimer = requestLatency.startTimer();
        logger.info("Client[" + request.getRemoteAddr() + "] request for App Info");
        try {
            AppInfo appInfo = appInfoService.getAppInfo();
            // Counter
            requests.inc();
            return ResponseEntity.unprocessableEntity().body(appInfo);
        } finally {
            receivedBytes.observe(request.getContentLength());
            requestTimer.observeDuration();
        }
    }
}
