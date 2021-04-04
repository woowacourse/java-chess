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

public class BishopTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initBoard();
    }

    @DisplayName("비숍이 빈 공간으로 제대로 이동하는지")
    @Test
    void moveBishop_blank_movePosition() {
        chessBoard.move(Position.of("b2"), Position.of("b3"));
        chessBoard.move(Position.of("c1"), Position.of("b2"));
        assertThat(chessBoard.getPiece(Position.of("b2")).getName()).isEqualTo("b");
    }

    @DisplayName("비숍이 이동하는 경로 도중에 아군이나 적군이 존재하면 에러를 반환 하는지")
    @Test
    void moveBishop_noBlankOnPath_throwError() {
        chessBoard.replace(Position.of("b3"), new Bishop(Color.WHITE, Position.of("b3")));

        chessBoard.replace(Position.of("d5"), new Pawn(Color.WHITE, Position.of("d5"))); // 아군
        assertThatThrownBy(() -> chessBoard.move(Position.of("b3"), Position.of("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);

        chessBoard.replace(Position.of("d5"), new Bishop(Color.BLACK, Position.of("d5"))); // 적군
        assertThatThrownBy(() -> chessBoard.move(Position.of("b3"), Position.of("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);
    }

    @DisplayName("비숍이 이동하는 자리에 아군이 존재하면 에러를 반환 하는지")
    @Test
    void moveBishop_allyAtDestination_throwError() {
        assertThatThrownBy(() -> chessBoard.move(Position.of("c1"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MOVABLE_POSITION_ERROR);
    }

    @DisplayName("비숍이 이동하는 자리에 적군이 존재하면 적군을 제대로 죽이는지")
    @Test
    void moveBishop_enemyAtDestination_movePosition() {
        chessBoard.replace(Position.of("a3"), new Bishop(Color.WHITE, Position.of("a3")));
        chessBoard.move(Position.of("a3"), Position.of("e7"));
        assertThat(chessBoard.getPiece(Position.of("e7")).getName()).isEqualTo("b");
    }
}
