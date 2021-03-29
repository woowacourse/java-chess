package chess.beforedb.domain.board;

import static chess.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.DBCleaner;
import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.game.ChessGame;
import chess.db.controller.dto.request.MoveRequestDTOForDB;
import chess.db.domain.game.ChessGameForDB;
import chess.utils.PositionConverter;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private ChessGameForDB chessGameForDB;
    private Long gameId;

    @BeforeEach
    void setUp() throws SQLException {
        chessGameForDB = new ChessGameForDB();
        gameId = chessGameForDB.createNew(new BoardDefaultSetting(), TEST_TITLE);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 빈 칸인 경우")
    @Test
    void cannotMovePieceAtStartPositionEmpty() throws SQLException {
        MoveRequestDTOForDB moveRequestDTOForDB = new MoveRequestDTOForDB(gameId, "a3", "a4");

        assertThatThrownBy(() -> chessGameForDB.move(moveRequestDTOForDB))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGameForDB.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a3")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 적의 기물이 있는 경우")
    @Test
    void cannotMovePieceAtStartPositionEnemyPiece() throws SQLException {
        MoveRequestDTOForDB moveRequestDTOForDB = new MoveRequestDTOForDB(gameId, "a7", "a6");

        assertThatThrownBy(() -> chessGameForDB.move(moveRequestDTOForDB))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGameForDB.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a7")))
            .isEqualTo("P");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a6")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 Pawn - 기물 이동")
    @Test
    void movePiece() throws SQLException {
        MoveRequestDTOForDB moveRequestDTOForDB = new MoveRequestDTOForDB(gameId, "a2", "a4");

        chessGameForDB.move(moveRequestDTOForDB);
        List<String> cellsStatus = chessGameForDB.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo("p");
    }

    @DisplayName("백 팀 Pawn - 기물이 이동할 수 없는 도착위치")
    @Test
    void cannotMovePieceToDestination() throws SQLException {
        MoveRequestDTOForDB moveRequestDTOForDB = new MoveRequestDTOForDB(gameId, "a2", "a5");

        assertThatThrownBy(() -> chessGameForDB.move(moveRequestDTOForDB))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGameForDB.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo("p");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a5")))
            .isEqualTo(".");
    }
}