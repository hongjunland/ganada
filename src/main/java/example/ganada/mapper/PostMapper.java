package example.ganada.mapper;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.CreatePostResponse;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    Post toEntity(CreatePostRequest request);


    Post of(CreatePostResponse response);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updatePostFromDto(@MappingTarget Post post, UpdatePostRequest request);

}
