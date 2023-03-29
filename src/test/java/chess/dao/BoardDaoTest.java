package chess.dao;

import chess.domain.ChessGame;
import chess.domain.RoomName;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.dto.BoardDto;
import chess.util.PieceRenderer;
import chess.util.SquareRenderer;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoardDaoTest {
    private final RoomName ROOM_NAME = new RoomName("test");
    private final BoardDao boardDao = new BoardDao();

    @BeforeEach
    void setup() {
        boardDao.deleteAllByRoomName(ROOM_NAME.getRoomName());
    }

    @AfterAll()
    void deleteAll() {
        boardDao.deleteAllByRoomName(ROOM_NAME.getRoomName());
    }

    @DisplayName("DB에 연결할 수 있다.")
    @Test
    void connectionTest() {
        try (Connection connection = boardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("DB에 변경된 체스판을 저장할 수 있다.")
    @Test
    void saveBoardTest() {
        ChessGame chessGame = new ChessGame(ROOM_NAME);
        Square source = Square.getInstanceOf(File.B, Rank.TWO);
        Square target = Square.getInstanceOf(File.B, Rank.THREE);

        chessGame.move(source, target);

        assertDoesNotThrow(() -> boardDao.save(createBoardDtoList(chessGame)));
    }

    @DisplayName("DB에서 체스판을 불러올 수 있다.")
    @Test
    void loadBoardTest() {
        assertDoesNotThrow(() -> boardDao.findAllByRoomName(ROOM_NAME.getRoomName()));
    }

    @DisplayName("DB에서 체스판을 업데이트할 수 있다.")
    @Test
    void updateBoardTest() {
        ChessGame chessGame = new ChessGame(ROOM_NAME);
        boardDao.save(createBoardDtoList(chessGame));

        Square source = Square.getInstanceOf(File.B, Rank.TWO);
        Square target = Square.getInstanceOf(File.B, Rank.THREE);
        chessGame.move(source, target);

        assertDoesNotThrow(() -> boardDao.update(createBoardDtoList(chessGame)));
    }

    private List<BoardDto> createBoardDtoList(ChessGame chessGame) {
        Chessboard board = chessGame.getChessboard();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Square square : board.getBoardMap().keySet()) {
            String source = SquareRenderer.render(square);
            String piece = PieceRenderer.render(board.getPieceAt(square));

            boardDtoList.add(new BoardDto(source, piece, chessGame.getRoomName()));
        }
        return boardDtoList;
    }
}
