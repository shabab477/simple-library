package com.example.simple.library.web.dto.response;

import com.example.simple.library.entities.AppUser;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserList implements Serializable {

    public static final long serialVersionUID = 1L;

    private List<MinimalAppUserInfo> minimalAppUserInfoList;

    public AppUserList(List<AppUser> appUsers) {
        this.minimalAppUserInfoList = appUsers.stream().map(MinimalAppUserInfo::new).collect(Collectors.toList());
    }

    public List<MinimalAppUserInfo> getAppUserInfoList() {
        return minimalAppUserInfoList;
    }

    public void setAppUserInfoList(List<MinimalAppUserInfo> minimalAppUserInfoList) {
        this.minimalAppUserInfoList = minimalAppUserInfoList;
    }
}
