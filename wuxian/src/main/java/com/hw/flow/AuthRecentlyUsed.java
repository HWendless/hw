package com.hw.flow;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hanwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AuthRecentlyUsed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userID;

    /**
     * 操作时间
     */
    private LocalDateTime perationTime;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用URL
     */
    private String mainPageUrl;

    private String id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public LocalDateTime getPerationTime() {
        return perationTime;
    }

    public void setPerationTime(LocalDateTime perationTime) {
        this.perationTime = perationTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
