package co.siten.base.web.rest;

import co.siten.base.domain.Demo;
import co.siten.base.repository.DemoRepository;
import co.siten.base.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.siten.base.domain.Demo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DemoResource {

    private final Logger log = LoggerFactory.getLogger(DemoResource.class);

    private static final String ENTITY_NAME = "demo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemoRepository demoRepository;

    public DemoResource(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    /**
     * {@code POST  /demos} : Create a new demo.
     *
     * @param demo the demo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demo, or with status {@code 400 (Bad Request)} if the demo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demos")
    public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) throws URISyntaxException {
        log.debug("REST request to save Demo : {}", demo);
        if (demo.getId() != null) {
            throw new BadRequestAlertException("A new demo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demo result = demoRepository.save(demo);
        return ResponseEntity.created(new URI("/api/demos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demos} : Updates an existing demo.
     *
     * @param demo the demo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demo,
     * or with status {@code 400 (Bad Request)} if the demo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demos")
    public ResponseEntity<Demo> updateDemo(@RequestBody Demo demo) throws URISyntaxException {
        log.debug("REST request to update Demo : {}", demo);
        if (demo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Demo result = demoRepository.save(demo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demos} : get all the demos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demos in body.
     */
    @GetMapping("/demos")
    public ResponseEntity<List<Demo>> getAllDemos(Pageable pageable) {
        log.debug("REST request to get a page of Demos");
        Page<Demo> page = demoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demos/:id} : get the "id" demo.
     *
     * @param id the id of the demo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demos/{id}")
    public ResponseEntity<Demo> getDemo(@PathVariable Long id) {
        log.debug("REST request to get Demo : {}", id);
        Optional<Demo> demo = demoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(demo);
    }

    /**
     * {@code DELETE  /demos/:id} : delete the "id" demo.
     *
     * @param id the id of the demo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demos/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable Long id) {
        log.debug("REST request to delete Demo : {}", id);
        demoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
