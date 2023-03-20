package chess.domain;

import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A5;
import static chess.domain.PositionFixture.A7;
import static chess.domain.PositionFixture.B2;
import static chess.domain.PositionFixture.B4;
import static chess.domain.PositionFixture.C1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

    @Test
    void 첫_뻔쨰_줄_테스트() {
        //given
        Board board = BoardGenerator.makeBoard();

        //when
        var result = board.getPiecePosition().get(0);
        //then

        var check = result.stream().map(PieceResponse::getPieceType).collect(Collectors.toList());
        assertThat(check)
                .containsExactly("r", "n", "b", "q", "k", "b", "n", "r")
                .hasSize(8);
    }

    @Test
    void 두_뻔쨰_줄_테스트() {
        //given
        Board board = BoardGenerator.makeBoard();

        //when
        var result = board.getPiecePosition().get(1);
        //then

        var check = result.stream().map(PieceResponse::getPieceType).collect(Collectors.toList());
        assertThat(check)
                .containsOnly("p")
                .hasSize(8);
    }

    @Test
    void 같은편_말이_있는_곳으로_이동할_수_없다() {
        //given
        Board board = BoardGenerator.makeBoard();

        //expect
        assertThatThrownBy(() -> board.movePiece(C1, A3))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 같은편_말이_없는_곳으로_이동할_수_있다() {
        //given
        Board board = BoardGenerator.makeBoard();
        board.movePiece(B2, B4);
        board.movePiece(A7, A5);
        board.movePiece(C1, A3);
    }
}
