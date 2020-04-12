package chess.dao;

import chess.domain.position.MovingPosition;

import java.util.LinkedHashMap;
import java.util.Map;

public class FakeHistoryDao {
	private final Map<Integer, MovingPosition> fakeHistoryDao;

	public FakeHistoryDao() {
		fakeHistoryDao = new LinkedHashMap<>();

		fakeHistoryDao.put(1, new MovingPosition("a2", "a4"));
		fakeHistoryDao.put(2, new MovingPosition("a7", "a6"));
		fakeHistoryDao.put(3, new MovingPosition("a4", "a5"));
		fakeHistoryDao.put(4, new MovingPosition("b7", "b5"));
	}

	public Map<Integer, MovingPosition> selectAll() {
		return fakeHistoryDao;
	}

	public void clear() {
		fakeHistoryDao.clear();
	}

	public void insert(String start, String end) {
		insert(new MovingPosition(start, end));
	}

	public void insert(MovingPosition movingPosition) {
		fakeHistoryDao.put(fakeHistoryDao.size() + 1, movingPosition);
	}
}
