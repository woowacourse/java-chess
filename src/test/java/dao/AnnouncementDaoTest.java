package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class AnnouncementDaoTest {
	private AnnouncementDao announcementDao;

	@BeforeEach
	void setUp() {
		announcementDao = AnnouncementDao.getInstance();
	}

	@Test
	void getConnection() {
		assertThat(announcementDao.getConnection()).isNotNull();
	}

	@Test
	void addAnnouncement() throws SQLException {
		final int resultNum = announcementDao.addAnnouncement("ㅋㅋㅋㅋㅋ", 1);
		assertThat(resultNum).isEqualTo(1);
	}

	@Test
	void setAnnouncementByRoomId() throws SQLException {
		final int resultNum = announcementDao.setAnnouncementByRoomId(1, "ㅎㅎㅎㅎㅎ");
		assertThat(resultNum).isEqualTo(1);
	}
}