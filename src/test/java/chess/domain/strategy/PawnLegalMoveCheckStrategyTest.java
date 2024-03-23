package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰 움직임 전략")
public class PawnLegalMoveCheckStrategyTest {

    private LegalMoveCheckStrategy legalMoveCheckStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        legalMoveCheckStrategy = new PawnLegalMoveCheckStrategy();
        board = new Board();
    }

    @DisplayName("폰은 올바른 위치가 입력되면 이동 가능을 반환한다.")
    @Test
    void pawnCorrectDestination() {
        // given
        Square source = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.A, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 잘못된 위치가 입력되면 이동 불가능을 반환한다.")
    @Test
    void pawnInCorrectDestination() {
        // given
        Square source = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.C, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 상대 말이 있을 경우 대각선 1칸이 입력되면 이동 가능을 반환한다.")
    @Test
    void moveOneDiagonal() {
        // given
        Square source = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.B, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }
}
