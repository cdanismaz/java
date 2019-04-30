package com.cd.rest.mapper;

import com.cd.rest.entitymodel.TypeEntity;
import com.cd.rest.model.Types;

public class TypeMapper {
    public static Types toType(TypeEntity typeEntity) {
        Types type = new Types();
        type.setId(typeEntity.getId());
        type.setName(typeEntity.getName());
        return type;
    }

    public static TypeEntity toTypeEntity(Types type) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(type.getId());
        typeEntity.setName(type.getName());
        return typeEntity;
    }
}
