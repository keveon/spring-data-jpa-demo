package com.keveon.demo.commons.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@ToString
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WorkingStatus {
    /**
     * 空闲
     */
    @JsonEnumDefaultValue
    idle(1, "空闲"),

    /**
     * 忙碌
     */
    busy(2, "忙碌"),

    /**
     * 休假
     */
    holiday(3, "休假");

    private final Integer code;
    private final String desc;

    @NotNull
    @JsonCreator
    public static WorkingStatus of(String code) {
        for (WorkingStatus value : values()) {
            if (value.getCode().toString().equals(code)) {
                return value;
            }
        }
        return idle;
    }
}
