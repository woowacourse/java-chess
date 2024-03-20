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

class KingTest {
    private static final Map<Position, Square> board = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    @DisplayName("킹은 한 칸 짜리 직선 경로이면 움직일 수 있다.")
    @Test
    void canStraightMoveTest() {
        // given
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 대각선 경로이면 움직일 수 있다.")
    @Test
    void canDiagonalMoveTest() {
        // given
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveTest() {
        // given
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isFalse();
    }
}
