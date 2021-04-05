package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Piece.NOT_MOVABLE_POSITION_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RookTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initBoard();
    }

    @DisplayName("룩이 빈 공간으로 제대로 이동하는지")
    @Test
    void moveRook_blank_movePosition() {
        chessBoard.move(Position.of("a2"), Position.of("a3"));
        chessBoard.move(Position.of("a1"), Position.of("a2"));
        assertThat(chessBoard.getPiece(Position.of("a2")).getName()).isEqualTo("r");
    }

    @DisplayName("룩이 이동하는 경로 도중에 아군이나 적군이 존재하면 에러를 반환 하는지")
    @Test
    void moveRook_noBlankOnPath_throwError() {
        chessBoard.replace(Position.of("b3"), new Rook(Color.WHITE, Position.of("b3")));

        chessBoard.replace(Position.of("b5"), new Pawn(Color.WHITE, Position.of("b5"))); // 아군
        assertThatThrownBy(() -> chessBoard.move(Position.of("b3"), Position.of("b6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);

        chessBoard.replace(Position.of("b5"), new Knight(Color.BLACK, Position.of("b5"))); // 적군
        assertThatThrownBy(() -> chessBoard.move(Position.of("b3"), Position.of("b6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);
    }

    @DisplayName("룩이 이동하는 자리에 아군이 존재하면 에러 반환 하는지")
    @Test
    void moveRook_allyAtDestination_throwError() {
        assertThatThrownBy(() -> chessBoard.move(Position.of("a1"), Position.of("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);
    }

    @DisplayName("룩이 이동하는 자리에 적이 있다면 적을 제대로 죽이는지")
    @Test
    void moveRook_enemyAtDestination_throwError() {
        chessBoard.replace(Position.of("b3"), new Rook(Color.WHITE, Position.of("b3")));
        chessBoard.move(Position.of("b3"), Position.of("b7"));
        assertThat(chessBoard.getPiece(Position.of("b7")).getName()).isEqualTo("r");
    }
}
