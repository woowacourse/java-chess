package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @Test
    void 행을_생성한다() {
        Rank rank = new Rank(RankCoordinate.ONE, List.of(new Square(FileCoordinate.C, Piece.BISHOP)));

        Square square = rank.getSquareByCoordinate(FileCoordinate.C);
        assertThat(square.getPiece()).isEqualTo(Piece.BISHOP);
    }
}
