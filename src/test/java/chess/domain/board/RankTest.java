package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @Test
    void 행을_생성한다() {
        Rank rank = new Rank(RankCoordinate.ONE,
                List.of(new Square(FileCoordinate.C, new Piece(PieceType.BISHOP, Color.BLACK))));

        Square square = rank.findSquareBy(FileCoordinate.C);
        Piece piece = square.getPiece();
        assertThat(piece.getPieceType()).isEqualTo(PieceType.BISHOP);
    }
}
