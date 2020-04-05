package chess.domain.web;

import java.util.LinkedHashMap;
import java.util.Map;

public class FakeLogDao {
	private final Map<Integer, Log> fakeLogDao;

	public FakeLogDao() {
		fakeLogDao = new LinkedHashMap<>();

		fakeLogDao.put(1, new Log("a2", "a4"));
		fakeLogDao.put(2, new Log("a7", "a6"));
		fakeLogDao.put(3, new Log("a4", "a5"));
		fakeLogDao.put(4, new Log("b7", "b5"));
	}

	public Map<Integer, Log> selectAll() {
		return fakeLogDao;
	}

	public void clear() {
		fakeLogDao.clear();
	}

	public void insert(String start, String end) {
		insert(new Log(start, end));
	}

	public void insert(Log log) {
		fakeLogDao.put(fakeLogDao.size() + 1, log);
	}
}
