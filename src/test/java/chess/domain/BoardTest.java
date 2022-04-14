package chess.domain;

import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    @DisplayName("기물들의 올바르게 점수를 계산하는지")
    void calculateScoreOfPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(PositionX.C, PositionY.RANK_2), new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0)); // 9
        pieces.put(Position.of(PositionX.C, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.KING, new KingMovingPattern(), 0)); // 0
        pieces.put(Position.of(PositionX.H, PositionY.RANK_4), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.H, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.G, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.C, PositionY.RANK_6), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.A, PositionY.RANK_2), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));
        pieces.put(Position.of(PositionX.A, PositionY.RANK_7), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));

        Board board = Board.of(pieces);
        double score = board.calculateScoreOf(Color.BLACK);

        assertThat(score).isEqualTo(12.0);
    }

    @Test
    @DisplayName("King 이 모두 살아있는 경우 true를 반환하는지")
    void checkAliveAllKings() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(PositionX.C, PositionY.RANK_2), new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0)); // 9
        pieces.put(Position.of(PositionX.C, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.KING, new KingMovingPattern(), 0)); // 0
        pieces.put(Position.of(PositionX.H, PositionY.RANK_4), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.H, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.G, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.C, PositionY.RANK_6), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.C, PositionY.RANK_1), new Piece(Color.WHITE, PieceType.KING, new KingMovingPattern(), 0)); // 0
        pieces.put(Position.of(PositionX.A, PositionY.RANK_2), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));
        pieces.put(Position.of(PositionX.A, PositionY.RANK_7), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));

        Board board = Board.of(pieces);

        assertThat(board.isBothKingsAlive()).isTrue();
    }

    @Test
    @DisplayName("King 이 하나라도 없는 경우 false를 반환하는지")
    void checkDeadKing() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(PositionX.C, PositionY.RANK_2), new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0)); // 9
        pieces.put(Position.of(PositionX.C, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.KING, new KingMovingPattern(), 0)); // 0
        pieces.put(Position.of(PositionX.H, PositionY.RANK_4), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.H, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.G, PositionY.RANK_3), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 0.5
        pieces.put(Position.of(PositionX.C, PositionY.RANK_6), new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0)); // 1
        pieces.put(Position.of(PositionX.A, PositionY.RANK_2), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));
        pieces.put(Position.of(PositionX.A, PositionY.RANK_7), new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0));

        Board board = Board.of(pieces);

        assertThat(board.isBothKingsAlive()).isFalse();
    }
}
