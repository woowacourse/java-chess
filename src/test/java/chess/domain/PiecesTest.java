package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PiecesTest {

    @ParameterizedTest
    @MethodSource("AllTypesPiece")
    @DisplayName("각 타입의 기물을 목적지로 옮긴다")
    void movePiece_sourceAndTargetGiven(final Piece piece, String source, String target) {
        Pieces pieces = new Pieces(() -> {
            Map<Position, Piece> testPieces = new HashMap<>();
            testPieces.put(Position.of(source), piece);
            return testPieces;
        });
        pieces.move(Position.of(source), Position.of(target));
        assertThat(pieces.findPiece(Position.of(target)).get()).isEqualTo(piece);
    }

    private static Stream<Arguments> AllTypesPiece() {
        return Stream.of(
                Arguments.of(new Piece(Color.WHITE, new Rook()), "a1", "a8"),
                Arguments.of(new Piece(Color.WHITE, new Bishop()), "a1", "e5"),
                Arguments.of(new Piece(Color.WHITE, new Queen()), "f5", "e4"),
                Arguments.of(new Piece(Color.WHITE, new Knight()), "f5", "d4"),
                Arguments.of(new Piece(Color.WHITE, new King()), "f5", "g4")
        );
    }

    @Test
    @DisplayName("킹이 잡힌다")
    void kingCaught() {
        Pieces pieces = new Pieces(() -> {
            Map<Position, Piece> testPieces = new HashMap<>();
            testPieces.put(Position.of("g7"), new Piece(Color.BLACK, new King()));
            testPieces.put(Position.of("d4"), new Piece(Color.WHITE, new King()));
            testPieces.put(Position.of("g1"), new Piece(Color.WHITE, new Rook()));
            return testPieces;
        });

        pieces.move(Position.of("g1"), Position.of("g7"));
        assertThat(pieces.kingCaught()).isTrue();
    }

    @Test
    @DisplayName("같은 세로줄에 복수 존재하는 같은 색의 폰의 수를 센다.")
    void calculate_penaltyPawns() {
        Pieces pieces = new Pieces(() -> {
            Map<Position, Piece> testPieces = new HashMap<>();
            testPieces.put(Position.of("g7"), new Piece(Color.BLACK, new King()));
            testPieces.put(Position.of("d4"), new Piece(Color.WHITE, new King()));
            testPieces.put(Position.of("a1"), new Piece(Color.BLACK, new Pawn()));
            testPieces.put(Position.of("a5"), new Piece(Color.WHITE, new Pawn()));
            testPieces.put(Position.of("a4"), new Piece(Color.WHITE, new Pawn()));
            testPieces.put(Position.of("b4"), new Piece(Color.WHITE, new Pawn()));
            return testPieces;
        });

        assertThat(pieces.countPenaltyPawns(Color.WHITE)).isEqualTo(2);
    }

}