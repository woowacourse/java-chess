package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.testutil.ChessPair;
import chess.domain.testutil.ChessPairsBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessRoundTest {
    @Test
    void move_source가_아군이고_target이_빈_칸인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Collections.emptyList());
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        ChessRound round = ChessRound.of(currentPlayer, opponentPlayer);

        round.move(source, target);

        assertThat(currentPlayer.contains(source)).isFalse();
        assertThat(currentPlayer.contains(target)).isTrue();
    }

    @Test
    void move_source가_아군이고_target이_적군인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(target, Queen.getInstance())
        ));
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        ChessRound round = ChessRound.of(currentPlayer, opponentPlayer);

        round.move(source, target);

        assertThat(currentPlayer.contains(source)).isFalse();
        assertThat(currentPlayer.contains(target)).isTrue();
        assertThat(opponentPlayer.contains(target)).isFalse();
    }

    @Test
    void move_source가_빈_칸인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Collections.emptyList());
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Collections.emptyList());
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        ChessRound round = ChessRound.of(currentPlayer, opponentPlayer);

        assertThrows(IllegalArgumentException.class, () -> round.move(source, target));
    }

    @Test
    void move_source가_적군인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Collections.emptyList());
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
        ));
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        ChessRound round = ChessRound.of(currentPlayer, opponentPlayer);

        assertThrows(IllegalArgumentException.class, () -> round.move(source, target));
    }

    @Test
    void move_source가_아군이고_target이_아군인_경우() {
        ChessPoint source = ChessPoint.of(1, 1);
        ChessPoint target = ChessPoint.of(1, 3);

        Map<ChessPoint, ChessPiece> chessPieces = ChessPairsBuilder.build(Arrays.asList(
                ChessPair.of(source, Queen.getInstance())
                , ChessPair.of(target, Knight.getInstance())
        ));
        ChessPlayer currentPlayer = ChessPlayer.from(chessPieces);

        Map<ChessPoint, ChessPiece> opponentChessPieces = ChessPairsBuilder.build(Collections.emptyList());
        ChessPlayer opponentPlayer = ChessPlayer.from(opponentChessPieces);

        ChessRound round = ChessRound.of(currentPlayer, opponentPlayer);

        assertThrows(IllegalArgumentException.class, () -> round.move(source, target));
    }
}