package chess.web;

import chess.dao.FakeHistoryDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeMovingPositionDaoTest {

	@DisplayName("selectAll 동작 확인")
	@ParameterizedTest
	@CsvSource(value = {"1,a2,a4", "2,a7,a6", "3,a4,a5", "4,b7,b5"})
	void selectAll_normal_test(int iter, String start, String end) {
		FakeHistoryDao fakeHistoryDao = new FakeHistoryDao();
		assertThat(fakeHistoryDao.selectAll().get(iter).getStart()).isEqualTo(start);
		assertThat(fakeHistoryDao.selectAll().get(iter).getEnd()).isEqualTo(end);

	}

	@DisplayName("clear 동작 확인")
	@Test
	public void clear_normal_test() {
		FakeHistoryDao fakeHistoryDao = new FakeHistoryDao();
		fakeHistoryDao.clear();
		assertTrue(fakeHistoryDao.selectAll().isEmpty());
	}

	@DisplayName("insert 동작 확인")
	@ParameterizedTest
	@CsvSource(value = {"1,a2,a4", "2,a7,a6", "3,a4,a5", "4,b7,b5", "5,b2,b4"})
	void insert_normal_test(int iter, String start, String end) {
		FakeHistoryDao fakeHistoryDao = new FakeHistoryDao();
		fakeHistoryDao.insert("b2", "b4");
		assertThat(fakeHistoryDao.selectAll().get(iter).getStart()).isEqualTo(start);
		assertThat(fakeHistoryDao.selectAll().get(iter).getEnd()).isEqualTo(end);

	}
}