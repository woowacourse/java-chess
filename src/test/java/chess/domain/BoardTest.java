package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
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
        pieces.put(new Position(PositionX.C, PositionY.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(new Position(PositionX.C, PositionY.RANK_3), new King(Color.BLACK)); // 0
        pieces.put(new Position(PositionX.H, PositionY.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.H, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.G, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.C, PositionY.RANK_6), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.A, PositionY.RANK_2), new Pawn(Color.WHITE));
        pieces.put(new Position(PositionX.A, PositionY.RANK_7), new Pawn(Color.WHITE));

        Board board = Board.of(pieces);
        double score = board.calculateScoreOf(Color.BLACK);

        assertThat(score).isEqualTo(12.0);
    }

    @Test
    @DisplayName("King 이 모두 살아있는 경우 true를 반환하는지")
    void checkAliveAllKings() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(new Position(PositionX.C, PositionY.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(new Position(PositionX.C, PositionY.RANK_3), new King(Color.BLACK)); // 0
        pieces.put(new Position(PositionX.H, PositionY.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.H, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.G, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.C, PositionY.RANK_6), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.C, PositionY.RANK_1), new King(Color.WHITE)); // 0
        pieces.put(new Position(PositionX.A, PositionY.RANK_2), new Pawn(Color.WHITE));
        pieces.put(new Position(PositionX.A, PositionY.RANK_7), new Pawn(Color.WHITE));

        Board board = Board.of(pieces);

        assertThat(board.isBothKingsAlive()).isTrue();
    }

    @Test
    @DisplayName("King 이 하나라도 없는 경우 false를 반환하는지")
    void checkDeadKing() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(new Position(PositionX.C, PositionY.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(new Position(PositionX.H, PositionY.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.H, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.G, PositionY.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(new Position(PositionX.C, PositionY.RANK_6), new Pawn(Color.BLACK)); // 1
        pieces.put(new Position(PositionX.C, PositionY.RANK_1), new King(Color.WHITE)); // 0
        pieces.put(new Position(PositionX.A, PositionY.RANK_2), new Pawn(Color.WHITE));
        pieces.put(new Position(PositionX.A, PositionY.RANK_7), new Pawn(Color.WHITE));

        Board board = Board.of(pieces);

        assertThat(board.isBothKingsAlive()).isFalse();
    }
}