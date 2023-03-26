package chess;

import chess.domain.Turn;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {
    @DisplayName("white 순서부터 시작된다.")
    @Test
    void turnStartWithWhite() {
        Turn turn = new Turn();
        Piece whitePiece = PieceType.PAWN.createPiece(Camp.WHITE);

        assertThat(turn.isMoveOrder(whitePiece))
                .isTrue();
    }

    @DisplayName("white 순서다음은 Black 순서이다.")
    @Test
    void turnChangeTest() {
        Turn turn = new Turn();
        Piece blackPiece = PieceType.PAWN.createPiece(Camp.BLACK);

        assertThat(turn.isMoveOrder(blackPiece))
                .isFalse();

        turn = turn.nextTurn();

        assertThat(turn.isMoveOrder(blackPiece))
                .isTrue();
    }
}
