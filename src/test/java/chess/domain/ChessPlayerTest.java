package chess.domain;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.testutil.ChessPair;
import chess.domain.testutil.ChessPairsBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPlayerTest {
    @Test
    void contains_말이_존재하는_경우() {
        ChessPoint containedPoint = ChessPoint.of(1, 1);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(containedPoint, King.getInstance())
                , ChessPair.of(ChessPoint.of(1, 2), Bishop.getInstance())
        ));
        ChessPlayer chessPlayer = ChessPlayer.from(chessPieces);

        assertThat(chessPlayer.contains(containedPoint)).isTrue();
    }

    @Test
    void contains_말이_존재하지_않는_경우() {
        ChessPoint notContainedPoint = ChessPoint.of(5, 5);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(ChessPoint.of(1, 1), King.getInstance())
                , ChessPair.of(ChessPoint.of(1, 2), Bishop.getInstance())
        ));
        ChessPlayer chessPlayer = ChessPlayer.from(chessPieces);

        assertThat(chessPlayer.contains(notContainedPoint)).isFalse();
    }
}