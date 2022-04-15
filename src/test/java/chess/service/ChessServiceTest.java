package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.room.Room;
import chess.dto.RoomsDto;
import chess.service.fake.FakeBoardDao;
import chess.service.fake.FakeMemberDao;
import chess.service.fake.FakePieceDao;
import chess.service.fake.FakeRoomDao;
import chess.service.fake.FakeSquareDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private final int fakeBoardId = 101;
    private final int fakeRoomId = 11;
    private final int fakeMemberId = 1;
    private final String fakeTitle = "woowa";
    private final String fakeName = "eden";

    private FakeBoardDao fakeBoardDao;
    private FakeSquareDao fakeSquareDao;
    private FakePieceDao fakePieceDao;
    private FakeRoomDao fakeRoomDao;
    private FakeMemberDao fakeMemberDao;
    private ChessService chessService;

    @BeforeEach
    void setup() {
        fakeBoardDao = new FakeBoardDao(fakeBoardId);
        fakeSquareDao = new FakeSquareDao();
        fakePieceDao = new FakePieceDao();
        fakeRoomDao = new FakeRoomDao(fakeRoomId, fakeTitle, fakeBoardId);
        fakeMemberDao = new FakeMemberDao(fakeMemberId, fakeName);
        chessService = new ChessService(fakeBoardDao, fakeSquareDao, fakePieceDao, fakeRoomDao, fakeMemberDao);
    }

    @Test
    void init() {
        Room room = chessService.init(fakeTitle, fakeName, "corinne");

        assertThat(room.getTitle()).isEqualTo(fakeTitle);
    }

    @Test
    void getRooms() {
        RoomsDto roomsDto = chessService.getRooms();

        assertThat(roomsDto.getRoomsDto().get(0).getRoomTitle()).isEqualTo(fakeTitle);
    }
}
