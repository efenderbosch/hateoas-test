package com.example;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildController {

	private final ChildRepository repo;
	private final PagedResourcesAssembler<Child> pagedResourcesAssembler = new PagedResourcesAssembler<>(null, null);

	@Autowired
	public ChildController(ChildRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/childrenWithPagination", method = GET, produces = "application/json")
	public ResponseEntity<?> getWithPagination(Pageable pageable) {
		Page<Child> children = repo.findAll(pageable);
		PagedResources<Resource<Child>> body = pagedResourcesAssembler.toResource(children);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@RequestMapping(value = "/childrenWithoutPagination", method = GET, produces = "application/json")
	public ResponseEntity<?> getWithoutPagination() {
		List<Child> children = repo.findAll();
		return new ResponseEntity<>(children, HttpStatus.OK);
	}

}
