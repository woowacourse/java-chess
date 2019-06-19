package chess.domain;

import chess.domain.chesspiece.*;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.testutil.ChessPair;
import chess.domain.testutil.ChessPairsBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void delete_말이_존재하는_경우() {
        ChessPoint containedPoint = ChessPoint.of(1, 1);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(containedPoint, King.getInstance())
                , ChessPair.of(ChessPoint.of(1, 2), Bishop.getInstance())
        ));
        ChessPlayer chessPlayer = ChessPlayer.from(chessPieces);

        chessPlayer.delete(containedPoint);

        assertThat(chessPlayer.contains(containedPoint)).isFalse();
    }

    @Test
    void delete_말이_존재하지_않는_경우() {
        ChessPoint notContainedPoint = ChessPoint.of(5, 5);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(ChessPoint.of(1, 1), King.getInstance())
                , ChessPair.of(ChessPoint.of(1, 2), Bishop.getInstance())
        ));
        ChessPlayer chessPlayer = ChessPlayer.from(chessPieces);

        assertThrows(IllegalArgumentException.class, () -> chessPlayer.delete(notContainedPoint));
    }

    @Test
    void move_단위_거리로_한_번_움직이는_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 2);
        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, King.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);
        ChessPlayer opponentPlayer = ChessPlayer.from(ChessPairsBuilder.build(Collections.emptyList()));

        currentPlayer.move(source, target, opponentPlayer);

        assertThat(currentPlayer.contains(source)).isFalse();
        assertThat(currentPlayer.contains(target)).isTrue();
    }

    @Test
    void move_단위_거리로_여러_번_움직이는_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);
        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);
        ChessPlayer opponentPlayer = ChessPlayer.from(ChessPairsBuilder.build(Collections.emptyList()));

        currentPlayer.move(source, target, opponentPlayer);

        assertThat(currentPlayer.contains(source)).isFalse();
        assertThat(currentPlayer.contains(target)).isTrue();
    }

    @Test
    void move_현재_위치의_말이_도달할_수_없는_target인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);
        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, King.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);
        ChessPlayer opponentPlayer = ChessPlayer.from(ChessPairsBuilder.build(Collections.emptyList()));

        assertThrows(IllegalArgumentException.class, () -> currentPlayer.move(source, target, opponentPlayer));
    }

    @Test
    void move_이동_경로에_아군이_있는_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint middle = ChessPoint.of(1, 2);
        ChessPoint target = ChessPoint.of(1, 3);
        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
                , ChessPair.of(middle, Knight.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);
        ChessPlayer opponentPlayer = ChessPlayer.from(ChessPairsBuilder.build(Collections.emptyList()));

        assertThrows(IllegalArgumentException.class, () -> currentPlayer.move(source, target, opponentPlayer));
    }

    @Test
    void move_이동_경로에_적군이_있는_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint middle = ChessPoint.of(1, 2);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(middle, Queen.getInstance())
        ));
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        assertThrows(IllegalArgumentException.class, () -> currentPlayer.move(source, target, opponentPlayer));
    }
}