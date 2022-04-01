package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopMoveStrategyTest {

    private Board board;
    private BishopMoveStrategy bishopMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.initialize());
        bishopMoveStrategy = new BishopMoveStrategy();
    }

    @Test
    @DisplayName("비숍이 이동할 수 있다.")
    void isMovable() {
        board.movePiece(Position.valueOf("d7"), Position.valueOf("d6"));

        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("양의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovablePositiveDiagonalIfExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("a6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("음의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovableNegativeDiagonalIfExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평 기물 확인은 지원하지 않는다.")
    void isHorizontalMoveUnsupported() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThatThrownBy(() -> bishopMoveStrategy.isPieceExistWhenHorizon(board, source, Distance.of(source, target)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("수직 기물 확인은 지원하지 않는다.")
    void isVerticalMoveUnsupported() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThatThrownBy(() -> bishopMoveStrategy.isPieceExistWhenVertical(board, source, Distance.of(source, target)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
