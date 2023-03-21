package ro.upb.saladeevenimente.service;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.SheetUserWorker;

import java.sql.SQLException;



public interface SheetUserWorkerService {
    SheetUserWorker addSheetUserWorker(Long userId) throws SQLException;
}
