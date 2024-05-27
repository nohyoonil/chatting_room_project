package com.example.chattingroom.oauth.model;

import java.util.Map;

public abstract class Oauth2UserInfo {

    protected Map<String, Object> attributes;

    public Oauth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getSocialId();

    public abstract String getName();

    public abstract String getEmail();
}
