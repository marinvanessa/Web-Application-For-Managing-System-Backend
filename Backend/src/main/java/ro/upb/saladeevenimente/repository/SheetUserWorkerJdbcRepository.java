package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.SheetUserWorker;

import java.sql.SQLException;

public interface SheetUserWorkerJdbcRepository {
    SheetUserWorker addSheetUserWorker(Long userId) throws SQLException;
}
