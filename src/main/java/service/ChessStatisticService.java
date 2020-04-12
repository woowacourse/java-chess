package service;

import dao.StatusRecordDao;
import dto.StatusRecordDto;

import java.sql.SQLException;
import java.util.List;

public class ChessStatisticService {
	private static final ChessStatisticService STATISTIC_SERVICE;

	static {
		STATISTIC_SERVICE = new ChessStatisticService(StatusRecordDao.getInstance());
	}

	private final StatusRecordDao statusRecordDao;

	public ChessStatisticService(final StatusRecordDao statusRecordDao) {
		this.statusRecordDao = statusRecordDao;
	}

	public static ChessStatisticService getInstance() {
		return STATISTIC_SERVICE;
	}

	public List<StatusRecordDto> loadStatusRecordsWithRoomName() throws SQLException {
		return statusRecordDao.findStatusRecords();
	}

}
