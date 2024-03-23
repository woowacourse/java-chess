package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비숍 움직임 전략")
class BishopLegalMoveCheckStrategyTest {

    private LegalMoveCheckStrategy legalMoveCheckStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        legalMoveCheckStrategy = new BishopLegalMoveCheckStrategy();
        board = new Board();
    }

    @DisplayName("비숍은 대각선 방향의 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void bishopCorrectDestination() {
        // given
        Square source = Square.of(File.E, Rank.SIX);
        Square destination = Square.of(File.H, Rank.THREE);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination,board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 대각선 방향이 아닌 위치가 들어오면 이동 불가능을 반환한다.")
    @Test
    void bishopInCorrectDestination() {
        // given
        Square source = Square.of(File.C, Rank.EIGHT);
        Square destination = Square.of(File.C, Rank.THREE);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }
}
