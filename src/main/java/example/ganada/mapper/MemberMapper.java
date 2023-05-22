package example.ganada.mapper;


import example.ganada.dto.member.CreateMemberRequest;
import example.ganada.dto.member.UpdateMemberRequest;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Member;
import example.ganada.entity.Post;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
