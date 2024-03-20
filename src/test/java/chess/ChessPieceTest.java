package chess;

import chess.domain.ChessPiece;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPieceTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK_KING, a1, a2", "BLACK_KING,a2,a1", "BLACK_KING,a1,b1", "BLACK_KING,b1,a1", "BLACK_KING,a1,b2", "BLACK_KING,b1,a2", "BLACK_KING,a2,b1", "BLACK_KING,b2,a1"
            , "BLACK_QUEEN, a1, a7", "BLACK_QUEEN, a7, a1", "BLACK_QUEEN, a1, h1", "BLACK_QUEEN, h1, a1", "BLACK_QUEEN, a1, h8", "BLACK_QUEEN, h8, a1", "BLACK_QUEEN, a8, h1", "BLACK_QUEEN, h1, a8"
            , "BLACK_KNIGHT,c3,d5", "BLACK_KNIGHT,c3,b5", "BLACK_KNIGHT,c3,d1", "BLACK_KNIGHT,c3,b1", "BLACK_KNIGHT,c3,e4", "BLACK_KNIGHT,c3,e2", "BLACK_KNIGHT,c3,a4", "BLACK_KNIGHT,c3,a2"
            , "BLACK_BISHOP, a1, h8", "BLACK_BISHOP, h8, a1", "BLACK_BISHOP, h1, a8", "BLACK_BISHOP, a8, h1"
            , "BLACK_ROOK, a1, a7", "BLACK_ROOK, a7, a1", "BLACK_ROOK, a1, h1", "BLACK_ROOK, h1, a1"
            , "BLACK_PAWN, a7, a6", "BLACK_PAWN, a7, a5", "BLACK_PAWN, a7, b6", "BLACK_PAWN, b7, a6", "BLACK_PAWN, b6, b5", "BLACK_PAWN, b6, a5", "BLACK_PAWN, b6, c5"
            , "WHITE_PAWN, a2, a3", "WHITE_PAWN, a2, a4", "WHITE_PAWN, a2, b3", "WHITE_PAWN, b2, a3", "WHITE_PAWN, b3, b4", "WHITE_PAWN, b3, a4", "WHITE_PAWN, b3, c4"})
    @DisplayName("체스말의 이동 규칙을 확인한다.")
    void ChessPiece_check_valid_moving_rule(ChessPiece chessPiece, Position source, Position target) {
        assertThat(chessPiece.isValidMovingRule(source, target)).isTrue();

    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK_KING, a1, a3"
            , "BLACK_QUEEN, a1, b5"
            , "BLACK_KNIGHT, c3, d6"
            , "BLACK_BISHOP, a1, h7"
            , "BLACK_ROOK, a1, b7"
            , "BLACK_PAWN, a7, a3"
            , "WHITE_PAWN, a2, c3"})
    @DisplayName("체스말의 이동 규칙을 확인한다.")
    void ChessPiece_check_invalid_moving_rule(ChessPiece chessPiece, Position source, Position target) {
        assertThat(chessPiece.isValidMovingRule(source, target)).isFalse();

    }
}
