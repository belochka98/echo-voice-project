package authenticationService.controller;

import authenticationService.mapper.RevisionMapper;
import authenticationService.repository.RevisionRepository;
import client.controller.RevisionController;
import component.response.ResultResponseFactory;
import dto.envers.RevisionChangeDto;
import dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class RevisionControllerImpl implements RevisionController {
    private final RevisionRepository revisionRepository;
    private final RevisionMapper revisionMapper;
    private final ResultResponseFactory responseFactory;

    @Override
    public ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) dto.envers.RevisionFilterDto filter) {
        return responseFactory.createResponseOk(revisionMapper.mapRevisionChanges(revisionRepository.findRevisions(filter)));
    }
}
