package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class SquareTest {

    @Test
    void 체스_칸을_생성한다() {
        Square square = new Square(FileCoordinate.A, new Piece(PieceType.BISHOP, Color.BLACK));

        assertAll(
                () -> assertThat(square.getPiece().getPieceType()).isEqualTo(PieceType.BISHOP),
                () -> assertThat(square.getFileCoordinate()).isEqualTo(FileCoordinate.A)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"A:true", "B:false"}, delimiter = ':')
    void 동일한_열_위치의_값인지_확인할_수_있다(FileCoordinate fileCoordinate, boolean expect) {
        Square square = new Square(FileCoordinate.A, new Piece(PieceType.BISHOP, Color.BLACK));

        assertThat(square.isSameWith(fileCoordinate)).isEqualTo(expect);
    }
}
