package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.domain.piece.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 피스_이름_가져오기_테스트() {
        //given
        Piece piece = PieceFactory.getInstance(PieceType.PAWN, Color.WHITE);

        //when
        PieceType result = piece.getType();

        //then
        assertThat(result)
                .isEqualTo(PieceType.PAWN);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK, -2", "WHITE, 2"})
    void 폰일_경우_처음에_2칸을_움직일_수_있다(Color color, int yChange) {
        //given
        Piece piece = PieceFactory.getInstance(PieceType.PAWN, color);
        Piece empty = Piece.empty();

        //expect
        assertDoesNotThrow(() -> piece.move(0, yChange, empty));
    }

    @ParameterizedTest
    @CsvSource(value = {"PAWN, 1", "KING, 0", "QUEEN, 9", "KNIGHT, 2.5", "ROOK, 5", "BISHOP, 3"})
    void 피스_점수_가져오기_테스트(PieceType pieceType, double score) {
        //given
        Piece piece = PieceFactory.getInstance(pieceType, Color.WHITE);

        //when
        double result = piece.getScore();

        //then
        assertThat(result)
                .isEqualTo(score, withPrecision(0.0001));
    }

    @Test
    void 움직일_수_없는_장소로_움직이면_예외가_발생한다() {
        //given
        Piece piece = PieceFactory.getInstance(PieceType.PAWN, Color.WHITE);
        Piece empty = Piece.empty();

        //expect
        assertThatThrownBy(() -> piece.move(0, 3, empty))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
