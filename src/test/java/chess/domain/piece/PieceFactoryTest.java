package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceFactoryTest {

    @DisplayName("PieceFactory에서 팀과 타입을 받아 Piece를 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "white, pawn, p",
            "white, rook, r",
            "white, knight, n",
            "white, bishop, b",
            "white, queen, q",
            "white, king, k",
            "black, pawn, P",
            "black, rook, R",
            "black, knight, N",
            "black, bishop, B",
            "black, queen, Q",
            "black, king, K",
            ", blank, ·"
    })
    void createPiece(String team, String pieceType, String signature) {
        Piece piece = PieceFactory.createPiece(team, pieceType, new Position(0, 0), false);
        assertThat(piece.getSignature()).isEqualTo(signature);
    }
}