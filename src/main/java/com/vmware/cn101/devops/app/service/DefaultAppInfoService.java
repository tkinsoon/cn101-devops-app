package com.vmware.cn101.devops.app.service;

import com.vmware.cn101.devops.app.model.AppInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.net.InetAddress;

@Component
public class DefaultAppInfoService implements AppInfoService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAppInfoService.class);
    private Environment environment;

    public DefaultAppInfoService(Environment environment) {
        this.environment = environment;
    }

    public AppInfo getAppInfo() throws Exception {
        logger.info("query for app environment info");
        AppInfo appInfo = new AppInfo();

        String port = environment.getProperty("server.port");
        if (port != null) {
            appInfo.setPort(Integer.parseInt(port));
        }
        // Local address
        appInfo.setLocalHostAddress(InetAddress.getLoopbackAddress().getHostAddress());
        appInfo.setLocalHostName(InetAddress.getLoopbackAddress().getHostName());
        // Remote address
        appInfo.setRemoteHostAddress(InetAddress.getLocalHost().getHostAddress());
        appInfo.setRemoteHostName(InetAddress.getLocalHost().getHostName());

        return appInfo;
    }
}
