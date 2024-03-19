package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceViewTest {

    @DisplayName("기물에 맞는 문자를 찾는다")
    @Test
    void findValue() {
        //given
        String expectedPawnValue1 = "p";
        String expectedPawnValue2 = "P";
        String expectedEmptyValue = ".";

        //when
        String pawnValue1 = PieceView.findValue(new Pawn(Color.WHITE, new Square(File.A, Rank.ONE)));
        String pawnValue2 = PieceView.findValue(new Pawn(Color.BLACK, new Square(File.A, Rank.ONE)));
        String emptyValue = PieceView.findValue(new EmptyPiece(new Square(File.A, Rank.ONE)));

        //then
        assertAll(
                () -> assertThat(pawnValue1).isEqualTo(expectedPawnValue1),
                () -> assertThat(pawnValue2).isEqualTo(expectedPawnValue2),
                () -> assertThat(emptyValue).isEqualTo(expectedEmptyValue)
        );
    }
}
