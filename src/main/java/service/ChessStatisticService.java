package service;

import dao.StatusRecordWithRoomNameDao;
import dto.StatusRecordWithRoomNameDto;

import java.sql.SQLException;
import java.util.List;

public class ChessStatisticService {
	private static final ChessStatisticService STATISTIC_SERVICE;

	static {
		STATISTIC_SERVICE = new ChessStatisticService(StatusRecordWithRoomNameDao.getInstance());
	}

	private final StatusRecordWithRoomNameDao statusRecordWithRoomNameDao;

	public ChessStatisticService(final StatusRecordWithRoomNameDao statusRecordWithRoomNameDao) {
		this.statusRecordWithRoomNameDao = statusRecordWithRoomNameDao;
	}

	public static ChessStatisticService getInstance() {
		return STATISTIC_SERVICE;
	}

	public List<StatusRecordWithRoomNameDto> loadStatusRecordsWithRoomName() throws SQLException {
		return statusRecordWithRoomNameDao.findStatusRecordWithRoomName();
	}

}
