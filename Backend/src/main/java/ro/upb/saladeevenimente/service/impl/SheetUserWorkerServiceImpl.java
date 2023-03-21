package ro.upb.saladeevenimente.service.impl;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.SheetUserWorker;
import ro.upb.saladeevenimente.repository.SheetUserWorkerJdbcRepository;
import ro.upb.saladeevenimente.repository.SheetUserWorkerJdbcRepositoryImpl;
import ro.upb.saladeevenimente.repository.UserJdbcRepository;
import ro.upb.saladeevenimente.repository.UserJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.SheetUserWorkerService;

import java.sql.SQLException;


@Service
public class SheetUserWorkerServiceImpl implements SheetUserWorkerService {


    private SheetUserWorkerJdbcRepository sheetUserWorkerJdbcRepository = new SheetUserWorkerJdbcRepositoryImpl();

    @Override
    public SheetUserWorker addSheetUserWorker(Long userId) throws SQLException {
        return sheetUserWorkerJdbcRepository.addSheetUserWorker(userId);
    }
}
