package ro.upb.saladeevenimente.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ro.upb.saladeevenimente.domain.SheetUserWorker;
import ro.upb.saladeevenimente.service.SheetUserWorkerService;

import java.sql.SQLException;

@RestController
@RequestMapping("/sheetUserWorkers")
public class SheetUserWorkerController {

    @Autowired
    private SheetUserWorkerService sheetUserWorkerService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/{userId}")
    public SheetUserWorker addSheetUserWorker(@PathVariable Long userId) throws SQLException {
        return sheetUserWorkerService.addSheetUserWorker(userId);
    }
}
