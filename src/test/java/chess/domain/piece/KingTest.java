package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class KingTest {
    @Test
    @DisplayName("King이 정상으로 생성되는지 테스트한다.")
    void init() {
        assertThatCode(() -> new King())
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5"})
    @DisplayName("King이 규칙상 움직일 수 있는 곳이라면, 참을 반환한다.")
    void when_king_can_move_according_to_rule_return_true(final String dest) {
        final King king = new King();
        final Position current = Position.of("d4");
        final Position destination = Position.of(dest);
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(current, king);

        assertThat(king.isMovable(current, destination, chessBoard)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5"})
    @DisplayName("King이 규칙상 움직일 수 업는 곳이라면, 거짓을 반환한다.")
    void when_king_cannot_move_according_to_rule_return_false(final String dest) {
        final King king = new King();
        final Position current = Position.of("g6");
        final Position destination = Position.of(dest);
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(current, king);

        assertThat(king.isMovable(current, destination, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("King이 킹사이드 캐슬링이 가능하다면, 참을 반환한다.")
    void when_king_side_castling_available_return_true() {
        final King whiteKing = new King();
        final Position kingCurrent = Position.of("e1");

        final Rook whiteRook = new Rook();
        final Position rookCurrent = Position.of("h1");

        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(kingCurrent, whiteKing);
        chessBoard.put(rookCurrent, whiteRook);

        final Position kingAfterCastling = Position.of("g1");
        assertThat(whiteKing.isMovable(kingCurrent, kingAfterCastling, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("King이 퀸사이드 캐슬링이 가능하다면, 참을 반환한다.")
    void when_queen_side_castling_available_return_true() {
        final King whiteKing = new King();
        final Position kingCurrent = Position.of("e1");

        final Rook whiteRook = new Rook();
        final Position rookCurrent = Position.of("a1");

        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(kingCurrent, whiteKing);
        chessBoard.put(rookCurrent, whiteRook);

        final Position kingAfterCastling = Position.of("c1");
        assertThat(whiteKing.isMovable(kingCurrent, kingAfterCastling, chessBoard)).isTrue();
    }
}
