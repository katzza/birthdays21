package org.levelp.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {
    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) {
        return UserStatus.valueOf(dbData.toUpperCase());
    }
}
