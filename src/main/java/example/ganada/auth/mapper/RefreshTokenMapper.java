package example.ganada.auth.mapper;

import example.ganada.auth.dto.RefreshTokenDto;
import example.ganada.auth.entity.RefreshToken;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RefreshTokenMapper {
    RefreshTokenMapper INSTANCE = Mappers.getMapper(RefreshTokenMapper.class);
    @Mappings({
            @Mapping(target = "refreshTokenId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    RefreshToken toEntity(RefreshTokenDto refreshTokenDto);
    @Mappings({
            @Mapping(target = "refreshTokenId", ignore = true),
            @Mapping(target = "member", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    void update(@MappingTarget RefreshToken token, RefreshTokenDto dto);
}
