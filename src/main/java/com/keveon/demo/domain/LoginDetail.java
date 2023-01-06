package com.keveon.demo.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * The type Login detail.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginDetail {

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 上次登录IP
     */
    @Size(max = 15)
    private String lastLoginIp;
}
