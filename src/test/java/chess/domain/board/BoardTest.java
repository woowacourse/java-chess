package chess.domain.board;

import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.domain.game.MoveCommand;
import chess.domain.piece.Pawn;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new ChessGame(new BoardDefaultSetting()).board();
    }

    @DisplayName("출발 위치에 자신의 기물이 없는 경우 이동 불가 - 빈 칸인 경우")
    @Test
    void cannotMovePieceAtStartPositionEmpty() {
        MoveRoute moveRoute = new MoveRoute("a3", "a4");
        MoveCommand moveCommand = new MoveCommand(WHITE, moveRoute);

        assertThatThrownBy(() -> board.move(moveCommand))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("출발 위치에 자신의 기물이 없는 경우이동 불가 - 적의 기물이 있는 경우")
    @Test
    void cannotMovePieceAtStartPositionEnemyPiece() {
        MoveRoute moveRoute = new MoveRoute("a7", "a6");
        MoveCommand moveCommand = new MoveCommand(WHITE, moveRoute);

        assertThatThrownBy(() -> board.move(moveCommand))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기물 이동")
    @Test
    void movePiece() {
        MoveRoute moveRoute = new MoveRoute("a2", "a4");
        MoveCommand moveCommand = new MoveCommand(WHITE, moveRoute);
        board.move(moveCommand);

        assertThat(board.findCell(Position.of("a2")).isEmpty()).isTrue();
        assertThat(board.findPiece(Position.of("a4"))).isEqualTo(new Pawn(WHITE));
    }

    @DisplayName("기물이 이동할 수 없는 도착위치")
    @Test
    void cannotMovePieceToDestination() {
        MoveRoute moveRoute = new MoveRoute("a2", "a5");
        MoveCommand moveCommand = new MoveCommand(WHITE, moveRoute);

        assertThatThrownBy(() -> board.move(moveCommand))
            .isInstanceOf(IllegalArgumentException.class);

        assertThat(board.findPiece(Position.of("a2"))).isEqualTo(new Pawn(WHITE));
        assertThat(board.findCell(Position.of("a5")).isEmpty()).isTrue();
    }
}