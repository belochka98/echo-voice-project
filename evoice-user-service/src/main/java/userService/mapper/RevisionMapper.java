package userService.mapper;

import dto.envers.RevisionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.history.Revision;
import userService.entity.envers.RevisionEntityCustom;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(componentModel = "spring", imports = {LocalDate.class, Instant.class, ZoneId.class, RevisionEntityCustom.class}, uses = RevisionMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RevisionMapper {
    @Mapping(target = "id", expression = "java(source.getRequiredRevisionNumber().longValue())")
    @Mapping(target = "operation", expression = "java(source.getMetadata().getRevisionType())")
    @Mapping(target = "date", expression = "java(LocalDate.ofInstant(source.getMetadata().getRequiredRevisionInstant(), ZoneId.systemDefault()))")
    @Mapping(target = "userName", expression = "java(((RevisionEntityCustom) source.getMetadata().getDelegate()).getUserName())")
    @Mapping(target = "object", expression = "java(source.getEntity())")
    RevisionDto apply(Revision source);

    Collection<RevisionDto> mapRevisions(Collection<Revision> source);
}
