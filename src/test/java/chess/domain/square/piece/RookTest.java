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

public class RookTest {
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

    @DisplayName("룩은 직선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveTest() {
        // given
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("룩은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackTest() {
        // given
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when
        assertThat(attackerPiece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.B), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when
        assertThat(attackerPiece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotMoveWithObstacleTest() {
        // given
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        Piece obstacle = Rook.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(attackerPiece.canMove(path, board))
                .isFalse();
    }
}
