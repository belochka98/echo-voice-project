package client.api;

import dto.envers.RevisionChangeDto;
import dto.filter.RevisionFilterDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public interface RevisionApi {
    ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter);
}