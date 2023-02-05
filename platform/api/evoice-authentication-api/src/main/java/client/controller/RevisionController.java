package client.controller;

import client.api.RevisionApi;
import dto.envers.RevisionChangeDto;
import dto.envers.RevisionFilterDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@RequestMapping("/revisions")
public interface RevisionController extends RevisionApi {
    ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter);
}
