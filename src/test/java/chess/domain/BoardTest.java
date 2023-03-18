package chess.domain;

import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A5;
import static chess.domain.PositionFixture.A7;
import static chess.domain.PositionFixture.B2;
import static chess.domain.PositionFixture.B4;
import static chess.domain.PositionFixture.C1;
import static chess.domain.PositionFixture.D1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.exception.IllegalPieceMoveException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardTest {

    @Test
    void 첫_뻔쨰_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPieces().get(0);
        //then

        var check = result.stream()
                .map(Piece::getType)
                .map(PieceType::getType)
                .collect(Collectors.toList());
        assertThat(check)
                .containsExactly("r", "n", "b", "q", "k", "b", "n", "r")
                .hasSize(8);
    }

    @Test
    void 두_뻔쨰_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPieces().get(1);
        //then

        var check = result.stream()
                .map(Piece::getType)
                .map(PieceType::getType)
                .collect(Collectors.toList());
        assertThat(check)
                .containsOnly("p")
                .hasSize(8);
    }

    @Test
    void 같은편_말이_있는_곳으로_이동할_수_없다() {
        //given
        Board board = new Board();

        //expect
        assertThatThrownBy(() -> board.movePiece(C1, A3))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 같은편_말이_없는_곳으로_이동할_수_있다() {
        //given
        Board board = new Board();

        assertDoesNotThrow(() -> {
            board.movePiece(B2, B4);
            board.movePiece(A7, A5);
            board.movePiece(C1, A3);
        });
    }

    @Test
    void 같은편_말이_없는_곳으로_이동할_수_있다2() {
        //given
        Board board = new Board();

        assertDoesNotThrow(() -> {
            board.movePiece(B2, B4);
            board.movePiece(C1, A3);
            board.movePiece(D1, C1);
        });
    }
}
