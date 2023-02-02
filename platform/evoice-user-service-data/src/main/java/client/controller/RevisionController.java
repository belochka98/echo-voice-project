package client.controller;

import client.api.RevisionApi;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/revisions")
public interface RevisionController extends RevisionApi {
}
