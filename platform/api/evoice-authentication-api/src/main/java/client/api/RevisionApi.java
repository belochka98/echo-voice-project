package client.api;

import dto.envers.RevisionChangeDto;
import dto.envers.RevisionFilterDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public interface RevisionApi {
    @GetMapping("/expanded")
    ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter);
}
