package com.sportshop.utils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public abstract class MsgUtil {

    private static final String BASE_NAME = "messages_en";
    public static final String SPLIT = "###";

    private MsgUtil() {
    }

    private static String getMessage(String code, Locale locale, Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
        String message;
        try {
            message = resourceBundle.getString(code);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            message = code;
        }
        return MessageFormatter.arrayFormat(code + SPLIT + message, args).getMessage();
    }

    public static String getMessage(String code) {
        return getMessage(code, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, LocaleContextHolder.getLocale(), args);
    }
}