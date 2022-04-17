package com.quangsang.springbaseexample.utils;

import com.quangsang.springbaseexample.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    private static ResourceBundleMessageSource messageSource;

    @Autowired
    public MessageUtil(ResourceBundleMessageSource msg) {
        MessageUtil.messageSource = msg;
    }

    public static String getMessage(String code, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }

    public static String getMessage(String code, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }

    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static String getMessage(ErrorCode errorCode, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode.getMessage(), args, locale);
    }

    public static String getMessage(ErrorCode errorCode, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode.getMessage(), args, locale);
    }

    public static String getMessage(ErrorCode errorCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode.getMessage(), null, locale);
    }
}
