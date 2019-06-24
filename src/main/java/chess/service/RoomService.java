package chess.service;

import chess.dao.RoomDao;
import chess.domain.Piece;
import chess.dto.RoomDto;

import java.util.List;

public class RoomService {
	private static final boolean ONGOING_STATUS = false;

	private RoomDao roomDao;

	public RoomService(final RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public void openRoom() {
		roomDao.add();
	}

	public long latestId() {
		return roomDao.getLatestId().orElseThrow(IllegalArgumentException::new);
	}

	public void updateStatus(final long roomId, final Piece.Color color) {
		roomDao.updateStatus(roomId, color.getName());
	}

	public List<RoomDto> findAllByOngoing() {
		return roomDao.findAllByStatus(ONGOING_STATUS);
	}

	public void deleteAll() {
		roomDao.deleteAll();
	}
}
