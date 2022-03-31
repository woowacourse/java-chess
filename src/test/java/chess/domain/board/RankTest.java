package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("8개가 아닌 기물을 가질 경우 예외가 발생한다.")
    @Test
    void validateSize() {
        List<Piece> pieces = List.of(
                new Blank(new Position("a1")),
                new Blank(new Position("b1")),
                new Blank(new Position("c1")),
                new Blank(new Position("d1")),
                new Blank(new Position("e1")),
                new Blank(new Position("f1")),
                new Blank(new Position("g1")),
                new Blank(new Position("h1")),
                new Blank(new Position("h2"))
        );

        assertThatThrownBy(() -> new Rank(pieces))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 랭크는 8칸 이어야 합니다.");
    }
}
