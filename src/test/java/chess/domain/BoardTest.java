package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardTest {

    @Test
    void 보드를_생성한다() {
        Piece piece = new Piece(PieceType.BISHOP, Color.BLACK);
        Square square = new Square(FileCoordinate.C, piece);
        Rank rank = new Rank(RankCoordinate.ONE, List.of(square));
        Board board = new Board(List.of(rank));

        assertThat(board.getRanks()).containsExactly(rank);
    }
}
