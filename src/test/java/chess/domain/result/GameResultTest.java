package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private static Map<Position, Piece> INITIALIZED_POSITIONS;
    private GameResult gameResult = new GameResult();

    @BeforeEach
    void setUp() {
        INITIALIZED_POSITIONS = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                String position = (char)(col + 96) + String.valueOf(row);
                INITIALIZED_POSITIONS.put(Position.of(position), Piece.of(PieceType.BLANK));
            }
        }
        INITIALIZED_POSITIONS.put(Position.of("e5"), Piece.of(PieceType.WHITE_BISHOP));
        INITIALIZED_POSITIONS.put(Position.of("g7"), Piece.of(PieceType.BLACK_ROOK));
        INITIALIZED_POSITIONS.put(Position.of("a1"), Piece.of(PieceType.WHITE_QUEEN));
        INITIALIZED_POSITIONS.put(Position.of("e8"), Piece.of(PieceType.WHITE_KING));
        INITIALIZED_POSITIONS.put(Position.of("d4"), Piece.of(PieceType.WHITE_ROOK));
        INITIALIZED_POSITIONS.put(Position.of("f5"), Piece.of(PieceType.BLACK_BISHOP));
        INITIALIZED_POSITIONS.put(Position.of("e3"), Piece.of(PieceType.WHITE_PAWN));
        INITIALIZED_POSITIONS.put(Position.of("b5"), Piece.of(PieceType.BLACK_KNIGHT));
        INITIALIZED_POSITIONS.put(Position.of("f7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산")
    @Test
    void result() {
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Team.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Team.BLACK)).isEqualTo(11.5);
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산 : 같은 열에 pawn 여러개 있을 경우 0.5로 계산")
    @Test
    void resultConsideringPawn() {
        INITIALIZED_POSITIONS.put(Position.of("f6"), Piece.of(PieceType.BLACK_PAWN));
        INITIALIZED_POSITIONS.put(Position.of("f8"), Piece.of(PieceType.BLACK_PAWN));

        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Team.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Team.BLACK)).isEqualTo(12);
    }
}
