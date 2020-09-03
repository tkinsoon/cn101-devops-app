package com.vmware.cn101.devops.app.service;

import com.vmware.cn101.devops.app.model.AppInfo;

public interface AppInfoService extends Service {

    public AppInfo getAppInfo() throws Exception;
}
