package userService.controller;

import userService.controller.utills.response.ResultResponse;
import userService.dto.envers.RevisionChangeDto;
import userService.dto.filter.RevisionFilterDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public interface RevisionController {
    ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter);
}
