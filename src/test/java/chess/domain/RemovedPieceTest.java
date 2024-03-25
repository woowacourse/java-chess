package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RemovedPieceTest {

    private static Stream<Arguments> isRecentlyRemovedPieceTypeParameters() {
        return Stream.of(
                Arguments.of(PieceType.KING, true),
                Arguments.of(PieceType.QUEEN, false)
        );
    }

    @DisplayName("최근에 잡힌 말의 종류를 판단한다.")
    @ParameterizedTest
    @MethodSource("isRecentlyRemovedPieceTypeParameters")
    void isRecentlyRemovedPieceTypeTest(PieceType pieceType, boolean expectedTypeCheck) {
        RemovedPiece removedPiece = new RemovedPiece();
        King king = new King(new PieceInfo(Position.of("a5"), Team.WHITE));
        Queen queen = new Queen(new PieceInfo(Position.of("a4"), Team.WHITE));

        removedPiece.addPiece(queen);
        removedPiece.addPiece(king);

        boolean actualTypeCheck = removedPiece.isRecentlyRemovedPieceType(pieceType);

        Assertions.assertThat(actualTypeCheck).isEqualTo(expectedTypeCheck);
    }
}
