package echoVoice.mapper;

import echoVoice.dto.envers.RevisionChangeDto;
import echoVoice.dto.envers.RevisionDto;
import echoVoice.entity.envers.RevisionChange;
import echoVoice.entity.envers.RevisionEntityCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.history.Revision;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(componentModel = "spring", imports = {LocalDate.class, Instant.class, ZoneId.class, RevisionEntityCustom.class}, uses = RevisionMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RevisionMapper {
    RevisionMapper MAPPER = Mappers.getMapper(RevisionMapper.class);

    @Mapping(target = "id", expression = "java(revision.getRequiredRevisionNumber().longValue())")
    @Mapping(target = "operation", expression = "java(revision.getMetadata().getRevisionType())")
    @Mapping(target = "date", expression = "java(LocalDate.ofInstant(revision.getMetadata().getRequiredRevisionInstant(), ZoneId.systemDefault()))")
    @Mapping(target = "userName", expression = "java(((RevisionEntityCustom) revision.getMetadata().getDelegate()).getUserName())")
    @Mapping(target = "object", expression = "java(revision.getEntity())")
    RevisionDto apply(Revision revision);

    Collection<RevisionDto> mapRevisions(Collection<Revision> revisions);


    @Mapping(target = "revisionId", source = "revision.id")
    @Mapping(target = "revisionDate", expression = "java(Instant.ofEpochMilli(revisionChange.getRevision().getId()).atZone(ZoneId.systemDefault()).toLocalDate())")
    @Mapping(target = "revisionOperation", source = "operation")
    RevisionChangeDto apply(RevisionChange revisionChange);

    Collection<RevisionChangeDto> mapRevisionChanges(Collection<RevisionChange> revisionChanges);
}
