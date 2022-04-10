package chess.domain.board.piece;

import static chess.domain.board.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class NonPawnTest {

    private static final Piece KNIGHT = Piece.of(WHITE, PieceType.KNIGHT);
    private static final Piece BISHOP = Piece.of(WHITE, PieceType.BISHOP);
    private static final Piece ROOK = Piece.of(WHITE, PieceType.ROOK);
    private static final Piece QUEEN = Piece.of(WHITE, PieceType.QUEEN);
    private static final Piece KING = Piece.of(WHITE, PieceType.KING);

    @ParameterizedTest
    @ValueSource(strings = {"b2", "b4", "c1", "c5", "e1", "e5", "f2", "f4"})
    void 나이트는_상하좌우_한칸_이동_후_해당_방향의_대각선으로_한칸_이동가능(String targetPositionKey) {
        Position from = Position.of("d3");
        Position to = Position.of(targetPositionKey);

        boolean actual = KNIGHT.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a3", "d1", "c4"})
    void 나이트는_대각선_수직_등의_방향으로는_이동불가(String targetPositionKey) {
        Position from = Position.of("d3");
        Position to = Position.of(targetPositionKey);

        boolean actual = KNIGHT.canMove(from, to);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b2", "b6", "f6", "f2"})
    void 비숍은_대각선으로_자유롭게_이동가능(String targetPositionKey) {
        Position from = Position.of("d4");
        Position to = Position.of(targetPositionKey);

        boolean actual = BISHOP.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a5", "e1", "b3"})
    void 비숍은_대각선이_아닌_방향으로는_이동불가(String targetPositionKey) {
        Position from = Position.of("a1");
        Position to = Position.of(targetPositionKey);

        boolean actual = BISHOP.canMove(from, to);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"c7", "c1", "a4", "e4"})
    void 룩은_수직방향으로_자유롭게_이동가능(String targetPositionKey) {
        Position from = Position.of("c4");
        Position to = Position.of( targetPositionKey);

        boolean actual = ROOK.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b2", "c3", "b3"})
    void 룩은_수직이_아닌_방향으로는_이동불가(String targetPositionKey) {
        Position from = Position.of("a1");
        Position to = Position.of(targetPositionKey);

        boolean actual = ROOK.canMove(from, to);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b2", "d4", "a2", "a5"})
    void 퀸은_대각선_혹은_수직방향으로_몇칸이든_이동가능(String targetPositionKey) {
        Position from = Position.of("a1");
        Position to = Position.of(targetPositionKey);

        boolean actual = QUEEN.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b3", "d5", "c8"})
    void 퀸은_대각선_혹은_수직이_아닌_방향으로는_이동불가(String targetPositionKey) {
        Position from = Position.of("a1");
        Position to = Position.of(targetPositionKey);

        boolean actual = QUEEN.canMove(from, to);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"})
    void 킹은_자유롭게_한칸_이동가능(String targetPositionKey) {
        Position from = Position.of("b2");
        Position to = Position.of(targetPositionKey);

        boolean actual = KING.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a3", "b5", "c1"})
    void 킹은_두칸_이상은_이동불가(String targetPositionKey) {
        Position from = Position.of("a1");
        Position to = Position.of(targetPositionKey);

        boolean actual = KING.canMove(from, to);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"KNIGHT", "BISHOP", "ROOK", "QUEEN", "KING"})
    void canMove_메서드는_같은_위치로_이동하려는_경우_거짓_반환(String pieceType) {
        Piece piece = Piece.of(WHITE, PieceType.valueOf(pieceType));
        Position samePosition = Position.of("a1");

        boolean actual = piece.canMove(samePosition, samePosition);

        assertThat(actual).isFalse();
    }
}
