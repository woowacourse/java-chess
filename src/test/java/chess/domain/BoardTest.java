package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PlayingCamp;
import chess.domain.piece.move_rule.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.move_rule.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void 보드의_현재_점수상태를_계산한다() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A5, new Piece(KingMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(B2, new Piece(BishopMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(A1, new Piece(RookMoveRule.getInstance(), PlayingCamp.BLACK));
        //3 + 5 = 8
        pieces.put(G1, new Piece(KingMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(G3, new Piece(QueenMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(H4, new Piece(KnightMoveRule.getInstance(), PlayingCamp.WHITE));
        //9 + 2.5 = 11.5
        Board board = new Board(pieces);

        // then
        assertThat(board.calculateScore()).containsExactly(8D, 11.5D);
    }

    @Test
    void 보드의_현재_점수상태를_계산_같은색폰이_같은세로줄에_있는경우() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A5, new Piece(KingMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(B2, new Piece(BishopMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(C1, new Piece(KnightMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(A1, new Piece(PawnMoveRule.from(PlayingCamp.BLACK), PlayingCamp.BLACK));
        pieces.put(D1, new Piece(PawnMoveRule.from(PlayingCamp.BLACK), PlayingCamp.BLACK));
        pieces.put(D4, new Piece(PawnMoveRule.from(PlayingCamp.BLACK), PlayingCamp.BLACK));
        //3 + 2.5 + 0.5 + 0.5 + 1 = 7.5

        pieces.put(G1, new Piece(KingMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(G3, new Piece(QueenMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(H4, new Piece(KnightMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(B3, new Piece(PawnMoveRule.from(PlayingCamp.WHITE), PlayingCamp.WHITE));
        //9 + 2.5 + 1 = 12.5
        Board board = new Board(pieces);

        // then
        assertThat(board.calculateScore()).containsExactly(7.5D, 12.5D);
    }

    @Test
    void 왕이_잡히면_보드는_finish_상태가_된다() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A5, new Piece(KingMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(B2, new Piece(BishopMoveRule.getInstance(), PlayingCamp.BLACK));
        pieces.put(C1, new Piece(KnightMoveRule.getInstance(), PlayingCamp.BLACK));

        pieces.put(G1, new Piece(KingMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(A3, new Piece(QueenMoveRule.getInstance(), PlayingCamp.WHITE));
        pieces.put(H4, new Piece(KnightMoveRule.getInstance(), PlayingCamp.WHITE));
        Board board = new Board(pieces);

        //when
        board.move(A3, A5, PlayingCamp.WHITE);

        //then
        assertThat(board.isFinished()).isTrue();
    }
}
