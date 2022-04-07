package web.dao;

import web.dto.GameInfoDto;
import web.dto.PieceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BoardDaoTest {

    private static final String ROOM_NAME = "Room1";
    private static final String POSITION = "a1";
    private final PieceDto pieceDto = new PieceDto(ROOM_NAME, POSITION, "pawn", "WHITE");
    private final PieceDto pieceDto2 = new PieceDto(ROOM_NAME, "a2", "pawn", "WHITE");
    private GameDao gameDao;
    private PieceDao boardDao;

    @BeforeEach
    void beforeEach() {
        gameDao = new GameDao();
        gameDao.save(new GameInfoDto(ROOM_NAME));
        boardDao = new PieceDao();
    }

    @Test
    @DisplayName("말 정보를 올바르게 저장한다.")
    void save() {
        assertThatCode(() -> boardDao.save(pieceDto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("원하는 말 정보를 찾아낸다.")
    void findByRoomNameAndPosition() {
        boardDao.save(pieceDto);
        PieceDto result = boardDao.findByRoomNameAndPosition(ROOM_NAME, POSITION);
        assertThat(result.getPieceType()).isEqualTo("pawn");
    }

    @Test
    @DisplayName("게임 룸에 존재하는 모든 말들을 불러온다.")
    void findAllPiecesByRoomName() {
        boardDao.save(pieceDto);
        boardDao.save(pieceDto);
        assertThat(boardDao.findAllPiecesByRoomName(ROOM_NAME).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("말 정보를 수정한다.")
    void update() {
        boardDao.save(pieceDto);
        boardDao.update(ROOM_NAME, POSITION, "a2");
        PieceDto result = boardDao.findByRoomNameAndPosition(ROOM_NAME, "a2");
        assertThat(result.getPieceType()).isEqualTo("pawn");
    }

    @Test
    @DisplayName("원하는 위치의 말 정보를 삭제한다.")
    void deleteByRoomNameAndPosition() {
        boardDao.save(pieceDto);
        boardDao.deleteByRoomNameAndPosition(ROOM_NAME, POSITION);
        PieceDto result = boardDao.findByRoomNameAndPosition(ROOM_NAME, POSITION);
        assertThat(result).isNull();
    }

    @AfterEach
    void afterEach() {
        gameDao.delete(ROOM_NAME);
    }
}
