package org.levelp.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/*нужен, чтобы конвертировать прописные из джавы в строчные в БД и обратно*/

@Converter(autoApply = false)
public class TeamConverter implements AttributeConverter<Team, String> {
    @Override
    public String convertToDatabaseColumn(Team attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public Team convertToEntityAttribute(String dbData) {
        return Team.valueOf(dbData.toUpperCase());
    }
}
