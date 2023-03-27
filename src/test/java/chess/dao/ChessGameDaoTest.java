package chess.dao;

import chess.controller.ChessBoardFormatter;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    void DB_연결을_확인한다() {
        try (final var connection = chessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (Exception e) {
            System.out.println("DB 연결에 실패하였습니다");
        }
    }

    @Test
    void 게임을_DB에_저장한다() {
        ChessBoardFormatter chessBoardFormatter = ChessBoardFormatter.from(new ChessBoard(ChessFactory.create()));
        RoomName testRoomName = new RoomName("test_room");

        assertDoesNotThrow(() ->
                chessGameDao.addGame(testRoomName, Team.WHITE, chessBoardFormatter.getOneLineChessBoard()));

        chessGameDao.deleteGame(testRoomName);
    }

    @Test
    void DB에서_방이름_목록을_불러온다() {
        assertDoesNotThrow(() -> chessGameDao.findRoomNames());
    }
}