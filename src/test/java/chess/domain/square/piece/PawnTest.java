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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private static final Map<Position, Square> board = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    // TODO: @Nested 로 분리하기
    @DisplayName("한 번도 이동하지 않은 블랙 폰은 밑으로 두 칸 움직일 수 있다.")
    @Test
    void blackCanMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.EIGHTH, File.A), piece);
        Path path = new Path(new Position(Rank.EIGHTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("이동한적이 있는 블랙 폰은 밑으로 두 칸 움직일 수 없다.")
    @Test
    void blackCanNotMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        piece.recognizeMoved();
        board.put(new Position(Rank.EIGHTH, File.A), piece);
        Path path = new Path(new Position(Rank.EIGHTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("블랙 폰은 항상 밑으로 한 칸 움직일 수 있다.")
    @Test
    void blackCanStraightMoveTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.EIGHTH, File.A), piece);
        Path path = new Path(new Position(Rank.EIGHTH, File.A), new Position(Rank.SEVENTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 밑으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidPathForBlack")
    void blackCanNotStraightMoveTest(Path path) {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), piece);

        // when & then
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    static Stream<Path> provideUnValidPathForBlack() {
        return Stream.of(
                new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.B)),
                new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.SEVENTH, File.A)),
                new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.SEVENTH, File.C)),
                new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.SIXTH, File.A))
        );
    }

    @DisplayName("한 번도 이동하지 않은 화이트 폰은 위로 두 칸 움직일 수 있다.")
    @Test
    void whiteCanMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.SECOND, File.A), piece);
        Path path = new Path(new Position(Rank.SECOND, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("이동한적이 있는 화이트 폰은 밑으로 두 칸 움직일 수 없다.")
    @Test
    void whiteCanNotMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        piece.recognizeMoved();
        board.put(new Position(Rank.SECOND, File.A), piece);
        Path path = new Path(new Position(Rank.SECOND, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    @DisplayName("화이트 폰은 항상 위로 한 칸 움직일 수 있다.")
    @Test
    void whiteCanStraightMoveTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(piece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 위로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidPathForWhite")
    void canStraightNotMoveTest(Path path) {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.SECOND, File.B), piece);

        // when & then
        assertThat(piece.canMove(path, board))
                .isFalse();
    }

    static Stream<Path> provideUnValidPathForWhite() {
        return Stream.of(
                new Path(new Position(Rank.SECOND, File.B), new Position(Rank.FIRST, File.B)),
                new Path(new Position(Rank.SECOND, File.B), new Position(Rank.SECOND, File.A)),
                new Path(new Position(Rank.SECOND, File.B), new Position(Rank.SECOND, File.C)),
                new Path(new Position(Rank.SECOND, File.B), new Position(Rank.FIRST, File.A))
        );
    }

    @DisplayName("블랙 폰은 왼쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanLeftDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        Piece attackedPiece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.EIGHTH, File.B), attackerPiece);
        board.put(new Position(Rank.SEVENTH, File.A), attackedPiece);

        Path path = new Path(new Position(Rank.EIGHTH, File.B), new Position(Rank.SEVENTH, File.A));

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 오른쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanRightDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        Piece attackedPiece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.EIGHTH, File.B), attackerPiece);
        board.put(new Position(Rank.SEVENTH, File.C), attackedPiece);

        Path path = new Path(new Position(Rank.EIGHTH, File.B), new Position(Rank.SEVENTH, File.C));

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForBlack")
    void blackCanNotAttackTest(Position attackedPosition) {
        // given
        Path path = new Path(new Position(Rank.SEVENTH, File.B), attackedPosition);
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        Piece attackedPiece = Pawn.createOnStart(Color.WHITE);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(attackedPosition, attackedPiece);

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isFalse();
    }

    static Stream<Position> provideUnValidAttackedPositionForBlack() {
        return Stream.of(
                new Position(Rank.EIGHTH, File.A),
                new Position(Rank.EIGHTH, File.C),
                new Position(Rank.SIXTH, File.B),
                new Position(Rank.EIGHTH, File.B),
                new Position(Rank.SEVENTH, File.A),
                new Position(Rank.SEVENTH, File.C)
        );
    }

    @DisplayName("화이트 폰은 왼쪽 대각선 한 칸 위를 공격할 수 있다.")
    @Test
    void whiteCanLeftDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        Piece attackedPiece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        Path path = new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 오른쪽 대각선 한 칸 위를 공격할 수 있다.")
    @Test
    void whiteCanRightDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        Piece attackedPiece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.C), attackedPiece);
        Path path = new Path(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.C));

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForWhite")
    void whiteCanNotAttackTest(Position attackedPosition) {
        // given
        Path path = new Path(new Position(Rank.SEVENTH, File.B), attackedPosition);
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        Piece attackedPiece = Pawn.createOnStart(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(attackedPosition, attackedPiece);

        // when & then
        assertThat(attackerPiece.canMove(path, board))
                .isFalse();
    }

    static Stream<Position> provideUnValidAttackedPositionForWhite() {
        return Stream.of(
                new Position(Rank.SIXTH, File.A),
                new Position(Rank.SIXTH, File.C),
                new Position(Rank.SIXTH, File.B),
                new Position(Rank.EIGHTH, File.B),
                new Position(Rank.SEVENTH, File.A),
                new Position(Rank.SEVENTH, File.C)
        );
    }
}
