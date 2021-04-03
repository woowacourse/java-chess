package chess.domain.board;

import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.dto.request.MoveRequestDTO;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.utils.DBCleaner;
import chess.utils.position.converter.PositionConverter;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private ChessGame chessGame;
    private Long gameId;

    @BeforeEach
    void setUp() throws SQLException {
        chessGame = new ChessGame();
        gameId = chessGame.createNew(new BoardDefaultSetting(), TEST_TITLE);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 빈 칸인 경우")
    @Test
    void cannotMovePieceAtStartPositionEmpty() throws SQLException {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(gameId, "a3", "a4");

        assertThatThrownBy(() -> chessGame.move(moveRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a3")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 적의 기물이 있는 경우")
    @Test
    void cannotMovePieceAtStartPositionEnemyPiece() throws SQLException {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(gameId, "a7", "a6");

        assertThatThrownBy(() -> chessGame.move(moveRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a7")))
            .isEqualTo("P");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a6")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 Pawn - 기물 이동")
    @Test
    void movePiece() throws SQLException {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(gameId, "a2", "a4");

        chessGame.move(moveRequestDTO);
        List<String> cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo("p");
    }

    @DisplayName("백 팀 Pawn - 기물이 이동할 수 없는 도착위치")
    @Test
    void cannotMovePieceToDestination() throws SQLException {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(gameId, "a2", "a5");

        assertThatThrownBy(() -> chessGame.move(moveRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo("p");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a5")))
            .isEqualTo(".");
    }
}