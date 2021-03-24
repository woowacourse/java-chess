package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.dto.request.CommandRequestDTO;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.utils.PositionConverter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private static final String MOVE = "move";

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new BoardDefaultSetting());
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 빈 칸인 경우")
    @Test
    void cannotMovePieceAtStartPositionEmpty() {
        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, "a3", "a4");

        assertThatThrownBy(() -> chessGame.move(commandRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a3")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 - 출발 위치에 자신의 기물이 없는 경우, 이동 불가 - 적의 기물이 있는 경우")
    @Test
    void cannotMovePieceAtStartPositionEnemyPiece() {
        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, "a7", "a6");

        assertThatThrownBy(() -> chessGame.move(commandRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a7")))
            .isEqualTo("P");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a6")))
            .isEqualTo(".");
    }

    @DisplayName("백 팀 Pawn - 기물 이동")
    @Test
    void movePiece() {
        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, "a2", "a4");

        chessGame.move(commandRequestDTO);
        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo(".");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo("p");
    }

    @DisplayName("백 팀 Pawn - 기물이 이동할 수 없는 도착위치")
    @Test
    void cannotMovePieceToDestination() {
        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, "a2", "a5");

        assertThatThrownBy(() -> chessGame.move(commandRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a2")))
            .isEqualTo("p");

        assertThat(cellsStatus.get(PositionConverter.convertToCellsStatusIndex("a4")))
            .isEqualTo(".");
    }
}