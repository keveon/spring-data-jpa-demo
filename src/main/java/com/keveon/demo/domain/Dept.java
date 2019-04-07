package com.keveon.demo.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 部门信息
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
@Table(name = "t_dept")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Dept extends AbstractPersistable<Integer> {
    /**
     * 部门名称
     */
    private String name;

    /**
     * 员工信息
     */
    @JsonUnwrapped
    @OneToMany(mappedBy = "dept")
    private List<Employee> employees;
}
