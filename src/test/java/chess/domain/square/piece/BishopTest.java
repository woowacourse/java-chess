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

class BishopTest {
    private static final Map<Position, Square> board = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    @DisplayName("비숍은 대각선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveTest() {
        // given
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("비숍은 대각선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveWithObstacleTest() {
        // given
        Piece piece = Bishop.from(Color.WHITE);
        Piece obstacle = Bishop.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }
}
