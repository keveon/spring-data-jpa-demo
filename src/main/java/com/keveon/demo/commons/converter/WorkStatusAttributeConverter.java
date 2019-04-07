package com.keveon.demo.commons.converter;

import com.keveon.demo.commons.consts.WorkingStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * {@link WorkingStatus} 与数据库中的 {@link Integer} 互相转换的映射类, 扩展自 {@link AttributeConverter}.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@Converter(autoApply = true)
public class WorkStatusAttributeConverter implements AttributeConverter<WorkingStatus, Integer> {

    @NotNull
    @Override
    public Integer convertToDatabaseColumn(WorkingStatus attribute) {
        return attribute.getCode();
    }

    @NotNull
    @Override
    public WorkingStatus convertToEntityAttribute(Integer dbData) {
        return WorkingStatus.of(ObjectUtils.nullSafeToString(dbData));
    }
}
