package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackPawnTest {
    @Test
    void checkRule_적군이_target에_있고_unit_direction이_맞지_않는_경우() {
        ChessPoint sourcePoint = ChessPoint.of(8, 2);
        ChessPoint targetPoint = ChessPoint.of(7, 2);
        boolean opponentPieceOnTarget = true;

        BlackPawn blackPawn = BlackPawn.getInstance();

        assertThat(blackPawn.checkRule(sourcePoint, targetPoint, opponentPieceOnTarget)).isFalse();
    }

    @Test
    void checkRule_적군이_target에_있고_unit_direction이_맞는_경우() {
        ChessPoint sourcePoint = ChessPoint.of(8, 2);
        ChessPoint targetPoint = ChessPoint.of(7, 1);
        boolean opponentPieceOnTarget = true;

        BlackPawn blackPawn = BlackPawn.getInstance();

        assertThat(blackPawn.checkRule(sourcePoint, targetPoint, opponentPieceOnTarget)).isTrue();
    }

    @Test
    void checkRule_적군이_target에_없고_unit_direction이_맞지_않는_경우() {
        ChessPoint sourcePoint = ChessPoint.of(8, 2);
        ChessPoint targetPoint = ChessPoint.of(7, 1);
        boolean opponentPieceOnTarget = false;

        BlackPawn blackPawn = BlackPawn.getInstance();

        assertThat(blackPawn.checkRule(sourcePoint, targetPoint, opponentPieceOnTarget)).isFalse();
    }

    @Test
    void checkRule_적군이_target에_없고_unit_direction이_맞는_경우() {
        ChessPoint sourcePoint = ChessPoint.of(8, 2);
        ChessPoint targetPoint = ChessPoint.of(7, 2);
        boolean opponentPieceOnTarget = false;

        BlackPawn blackPawn = BlackPawn.getInstance();

        assertThat(blackPawn.checkRule(sourcePoint, targetPoint, opponentPieceOnTarget)).isTrue();
    }

    @Test
    void checkRule_방향은_맞는데_여러_칸_이동하는_경우() {
        ChessPoint sourcePoint = ChessPoint.of(8, 2);
        ChessPoint targetPoint = ChessPoint.of(6, 2);
        boolean opponentPieceOnTarget = false;

        BlackPawn blackPawn = BlackPawn.getInstance();

        assertThat(blackPawn.checkRule(sourcePoint, targetPoint, opponentPieceOnTarget)).isFalse();
    }
}