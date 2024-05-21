package com.example.chattingroom.oauth;

import com.example.chattingroom.oauth.model.Oauth2UserInfo;
import com.example.chattingroom.oauth.model.impl.GoogleOauthUserInfo;
import com.example.chattingroom.type.Provider;

import java.util.Map;

public class Oauth2UserInfoFactory {
    public static Oauth2UserInfo getOAuth2UserInfo(Provider provider, Map<String, Object> attributes) {
        switch (provider) {
            case GOOGLE: return new GoogleOauthUserInfo(attributes);
            default: throw new RuntimeException();
        }
    }
}
