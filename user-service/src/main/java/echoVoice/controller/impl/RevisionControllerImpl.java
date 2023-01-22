package echoVoice.controller.impl;

import echoVoice.controller.RevisionController;
import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.controller.utills.response.ResultResponseFactory;
import echoVoice.dto.envers.RevisionChangeDto;
import echoVoice.dto.filter.RevisionFilterDto;
import echoVoice.mapper.RevisionMapper;
import echoVoice.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@RequestMapping("/revisions")
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class RevisionControllerImpl implements RevisionController {
    private final RevisionRepository revisionRepository;
    private final RevisionMapper revisionMapper;
    private final ResultResponseFactory responseFactory;

    @Override
    @GetMapping("/expanded")
    public ResultResponse<Collection<RevisionChangeDto>> getRevisionChanges(@RequestParam(required = false) RevisionFilterDto filter) {
        return responseFactory.createResponseOk(revisionMapper.mapRevisionChanges(revisionRepository.findRevisions(filter)));
    }
}
