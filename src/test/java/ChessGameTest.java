import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.ChessGame;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.position.Position;
import chess.dto.MovePositionCommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    ChessmenInitializer chessmenInitializer;
    ChessGame chessGame;

    @BeforeEach
    void setup_chessGame() {
        chessmenInitializer = new ChessmenInitializer();
        chessGame = ChessGame.of(chessmenInitializer.init());
    }

    @DisplayName("체스말이 이동할 수 있는 위치면 이동에 성공한다.")
    @Test
    void move_success() {
        MovePositionCommandDto command = new MovePositionCommandDto("move a2 a4");

        chessGame.moveChessmen(command);

        Piece actual = chessGame.getChessmen().extractPiece(Position.of("a4"));
        Pawn expected = new Pawn(WHITE, Position.of("a4"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("체스말이 이동할 수 없는 위치면 예외가 발생한다.")
    @Test
    void move_failOnInvalidMove() {
        MovePositionCommandDto command = new MovePositionCommandDto("move a2 a5");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("체스말의 이동경로에 다른 말이 있으면 예외가 발생한다.")
    @Test
    void move_failOnObstacleInPath() {
        MovePositionCommandDto command = new MovePositionCommandDto("move a1 a3");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("가는 길목에 다른 말이 있어 이동할 수 없습니다.");
    }

    @DisplayName("체스말의 이동하려는 위치에 아군말이 있으면 예외가 발생한다.")
    @Test
    void move_failOnFriendlyInTargetPosition() {
        MovePositionCommandDto command = new MovePositionCommandDto("move a1 a2");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동하려는 위치에 아군 말이 있습니다.");
    }

}
