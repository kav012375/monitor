package com.wulin.biz.common.service.impl;

import com.wulin.biz.common.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FengG on 16/6/20.
 */
public class SecurityServiceImpl implements SecurityService {

    public String Md5Creator(String input) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            int arryLenth = resultByteArray.length;
            char str[] = new char[arryLenth * 2];
            int stepCounter = 0;
            for (int step = 0; step < arryLenth; step++) {
                byte byte0 = resultByteArray[step];
                str[stepCounter++] = hexDigits[byte0 >>> 4 & 0xf];
                str[stepCounter++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }

    }

    public String GetRealIpAddr(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }
}
