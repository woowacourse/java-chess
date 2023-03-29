package chess.dao;

import chess.domain.ChessGame;
import chess.domain.RoomName;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.dto.BoardDto;
import chess.dto.GameRoomDto;
import chess.util.PieceRenderer;
import chess.util.SquareRenderer;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JdbcDaoTest {
    private final String ROOM_NAME = "test";
    private final JdbcDao jdbcDao = new JdbcDao();

    @BeforeEach
    void setup() {
        jdbcDao.deleteAllByName(ROOM_NAME);
    }

    @AfterAll()
    void deleteAll() {
        jdbcDao.deleteAllByName(ROOM_NAME);
    }

    @DisplayName("DB에 게임 정보를 저장할 수 있다.")
    @Test
    void saveTest() {
        ChessGame chessGame = new ChessGame(new RoomName(ROOM_NAME));
        Square source = Square.getInstanceOf(File.B, Rank.TWO);
        Square target = Square.getInstanceOf(File.B, Rank.THREE);
        GameRoomDto gameRoomDto = new GameRoomDto(ROOM_NAME, true);

        chessGame.move(source, target);

        assertDoesNotThrow(() -> jdbcDao.save(createBoardDtoList(chessGame), gameRoomDto));
    }

    @DisplayName("DB에 게임 정보를 업데이트할 수 있다.")
    @Test
    void updateTest() {
        ChessGame chessGame = new ChessGame(new RoomName(ROOM_NAME));
        GameRoomDto gameRoomDto = new GameRoomDto(ROOM_NAME, true);
        jdbcDao.save(createBoardDtoList(chessGame), gameRoomDto);

        Square source = Square.getInstanceOf(File.B, Rank.TWO);
        Square target = Square.getInstanceOf(File.B, Rank.THREE);
        chessGame.move(source, target);
        GameRoomDto changedGameRoomDto = new GameRoomDto(ROOM_NAME, false);


        assertDoesNotThrow(() -> jdbcDao.update(createBoardDtoList(chessGame), changedGameRoomDto));
    }

    @DisplayName("DB에서 게임 정보를 불러올 수 있다.")
    @Test
    void loadBoardTest() {
        assertDoesNotThrow(() -> jdbcDao.findBoardByRoomName(ROOM_NAME));
        assertDoesNotThrow(() -> jdbcDao.findGameRoomByName(ROOM_NAME));
    }

    private List<BoardDto> createBoardDtoList(ChessGame chessGame) {
        Chessboard board = chessGame.getChessboard();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Square square : board.getBoardMap().keySet()) {
            String source = SquareRenderer.render(square);
            String piece = PieceRenderer.render(board.getPieceAt(square));

            boardDtoList.add(new BoardDto(source, piece));
        }
        return boardDtoList;
    }
}
