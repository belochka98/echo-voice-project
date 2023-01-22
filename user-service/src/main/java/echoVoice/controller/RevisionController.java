package echoVoice.controller;

import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.dto.envers.RevisionChangeDto;
import echoVoice.dto.filter.RevisionFilterDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public interface RevisionController {
    ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter);
}
