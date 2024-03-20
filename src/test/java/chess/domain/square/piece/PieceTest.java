package chess.domain.square.piece;

import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    private static final Map<Position, Square> board = new HashMap<>();

    // TODO: indent 2 -> 1로 줄이기
    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    @DisplayName("아군은 공격할 수 없다.")
    @Test
    void canNotAttack() {
        // given
        Piece attacker = Rook.from(Color.BLACK);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attacker);
        board.put(new Position(Rank.SECOND, File.A), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(attacker.canAttack(path, board))
                .isFalse();
    }
}
