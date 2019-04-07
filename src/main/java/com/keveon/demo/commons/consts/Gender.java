package com.keveon.demo.commons.consts;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * The enum Gender.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@ToString
@RequiredArgsConstructor
public enum Gender {
    /**
     * 未知
     */
    @JsonEnumDefaultValue
    unknown(-1),
    /**
     * 女
     */
    female(0),
    /**
     * 男
     */
    male(1);

    private final Integer code;

    /**
     * Of gender.
     *
     * @param code the code
     * @return the gender
     */
    @NotNull
    public static Gender of(@NotNull Integer code) {
        for (Gender value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return unknown;
    }
}
