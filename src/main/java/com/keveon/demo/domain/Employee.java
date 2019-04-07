package com.keveon.demo.domain;

import com.keveon.demo.commons.consts.Gender;
import com.keveon.demo.commons.consts.WorkingStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * 员工信息
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_employee",
        indexes = {
                @Index(columnList = "phone"),
                @Index(columnList = "idCard")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"phone"}),
                @UniqueConstraint(columnNames = {"idCard"})
        }
)
public class Employee extends AbstractPersistable<Integer> {
    /**
     * 员工姓名
     */
    @Size(max = 20)
    private String name;
    /**
     * 员工手机号
     */
    @Size(max = 11)
    private String phone;
    /**
     * 身份证号码
     */
    @Size(max = 18)
    private String idCard;
    /**
     * 员工性别
     */
    private Gender gender;
    /**
     * 入职日期
     */
    private LocalDate entryDate;
    /**
     * 离职日期
     */
    private LocalDate turnoverDate;
    /**
     * 工作状态
     */
    private WorkingStatus status;
    /**
     * 登陆详情
     */
    @Embedded
    private LoginDetail loginDetail;
    /**
     * 所在部门
     */
    @ManyToOne
    private Dept dept;
}
