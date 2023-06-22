package example.ganada.member.mapper;


import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.member.dto.UpdateMemberRequest;
import example.ganada.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Member toEntity(CreateMemberRequest createMemberRequest);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateMemberFromDto(@MappingTarget Member member, UpdateMemberRequest request);
}
