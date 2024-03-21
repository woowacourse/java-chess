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

public class QueenTest {
    private static final Map<Position, Square> board = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    @DisplayName("퀸은 직선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canStraightMoveTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canDiagonalMoveTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveStraightWithObstacleTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveDiagonalWithObstacleTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("퀸은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canStraightAttackTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(attackerPiece.canAttack(path, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canDiagonalAttackTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canAttack(path, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.C), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when
        assertThat(attackerPiece.canAttack(path, board))
                .isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackStraightWithObstacleTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(attackerPiece.canAttack(path, board))
                .isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackDiagonalWithObstacleTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canAttack(path, board))
                .isFalse();
    }
}
