package pl.edu.pg.aui.employee.controller.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface CompanySyncController {

    @PutMapping("/companies/{companyId}")
    ResponseEntity<String> addCompany(@PathVariable UUID companyId);

    @DeleteMapping("/companies/{companyId}")
    ResponseEntity<String> deleteCategory(@PathVariable UUID companyId);
}
