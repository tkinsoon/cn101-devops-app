package com.vmware.cn101.devops.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {

    private int port;
    private String localHostAddress;
    private String localHostName;
    private String remoteHostAddress;
    private String remoteHostName;
}
