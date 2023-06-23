package example.ganada.member.mapper;


import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.member.dto.MemberResponse;
import example.ganada.member.dto.UpdateMemberRequest;
import example.ganada.member.entity.Member;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(target = "memberId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    Member toEntity(CreateMemberRequest createMemberRequest);

    @Mappings({
            @Mapping(target = "memberId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    MemberResponse toDto(@MappingTarget Member member);

    @Mappings({
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "firstName", ignore = true),
            @Mapping(target = "lastName", ignore = true),
            @Mapping(target = "nickname", ignore = true)
    })
    void update(UpdateMemberRequest dto, @MappingTarget Member member);

    @AfterMapping
    default void handleNonNullValues(UpdateMemberRequest dto, @MappingTarget Member entity) {
        if (dto.getEmail() != null)
            entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null)
            entity.setPassword(dto.getPassword());
        if (dto.getFirstName() != null)
            entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)
            entity.setLastName(dto.getLastName());
        if (dto.getNickname() != null)
            entity.setNickname(dto.getNickname());
    }
}
