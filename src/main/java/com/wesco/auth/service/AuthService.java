package com.wesco.auth.service;

import com.wesco.auth.mapper.AccessTokenRequest;
import com.wesco.auth.mapper.AccessTokenResponse;

public interface AuthService {
     AccessTokenResponse getAccessToken(AccessTokenRequest accessTokenRequest, String endPointurl);
}
