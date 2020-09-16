package ru.cityinfo.util.exception;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {

    private final ru.cityinfo.util.exception.ErrorType type;
    private final String msgCode;
    private final String[] args;

    public ApplicationException(String msgCode) {
        this(ru.cityinfo.util.exception.ErrorType.APP_ERROR, msgCode);
    }

    public ApplicationException(ru.cityinfo.util.exception.ErrorType type, String msgCode, String... args) {
        super(String.format("type=%s, msgCode=%s, args=%s", type, msgCode, Arrays.toString(args)));
        this.type = type;
        this.msgCode = msgCode;
        this.args = args;
    }

    public ru.cityinfo.util.exception.ErrorType getType() {
        return type;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String[] getArgs() {
        return args;
    }
}
