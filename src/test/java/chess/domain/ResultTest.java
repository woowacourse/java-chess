package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.ChessBoard.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultTest {

    @Test
    @DisplayName("체스 그룹을 입력하면 해당 체스들의 점수 합을 반환한다.")
    void shouldSucceedToCalculateScore() {

        Color color = Color.WHITE;
        Map<Position, ChessPiece> whitePieces = new HashMap<>();
        whitePieces.put(Position.findPosition(WHITE_ROOK_LEFT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_ROOK_RIGHT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_LEFT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_RIGHT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_LEFT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_RIGHT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_QUEEN), new Queen(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KING), new King(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIRST), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SECOND), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_THIRD), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FOURTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIFTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SIXTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SEVENTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_EIGHT), new Pawn(Color.WHITE));

        Result result = Result.of(whitePieces, color);

        assertThat(result.getScore()).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 컬럼에 존재할 때 해당 체스들의 점수 합을 정상적으로 계산한다.")
    void shouldSucceedToCalculateScoreWithPawnInSameLine() {

        Color color = Color.WHITE;
        Map<Position, ChessPiece> whitePieces = new HashMap<>();
        whitePieces.put(Position.findPosition(WHITE_ROOK_LEFT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_ROOK_RIGHT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_LEFT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_RIGHT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_LEFT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_RIGHT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_QUEEN), new Queen(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KING), new King(Color.WHITE));
        whitePieces.put(Position.findPosition("b3"), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SECOND), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_THIRD), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FOURTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIFTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SIXTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SEVENTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_EIGHT), new Pawn(Color.WHITE));

        Result result = Result.of(whitePieces, color);

        assertThat(result.getScore()).isEqualTo(34.0);
    }
}
