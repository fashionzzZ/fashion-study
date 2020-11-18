package com.fashion.excel.model;

public enum MsgTypeEnum {
    /**
     * 短信
     */
    SMS("SMS", "短信"),
    /**
     * 站内信
     */
    SYS("SYS", "站内信");

    private String code;
    private String name;

    MsgTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static MsgTypeEnum valueOfName(String name) {
        switch (name) {
            case "短信":
                return SMS;
            case "站内信":
                return SYS;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    @Override
    public String toString() {
        return code;
    }
}
