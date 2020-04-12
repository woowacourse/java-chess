package service;

import dao.AnnouncementDao;

import java.sql.SQLException;

public class AnnouncementService {
	private static final AnnouncementService ANNOUNCEMENT_SERVICE;

	static {
		ANNOUNCEMENT_SERVICE = new AnnouncementService(new AnnouncementDao());
	}

	private final AnnouncementDao announcementDao;

	private AnnouncementService(final AnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}

	public static AnnouncementService getInstance() {
		return ANNOUNCEMENT_SERVICE;
	}

	public String loadAnnouncementMessage(int roomId) throws SQLException {
		return announcementDao.findAnnouncementByRoomId(roomId).getMessage();
	}
}
