package chess.domain;

import chess.domain.boardcell.ChessPiece;
import chess.domain.boardcell.ChessPieceFactory;
import chess.domain.boardcell.PieceType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCounterTest {

    @Test
    void calculate() {
        ChessPiece rb = ChessPieceFactory.create(PieceType.ROOK_BLACK);
        ChessPiece bb = ChessPieceFactory.create(PieceType.BISHOP_BLACK);
        ChessPiece pb = ChessPieceFactory.create(PieceType.PAWN_BLACK);
        ChessPiece rw = ChessPieceFactory.create(PieceType.ROOK_WHITE);
        ChessPiece bw = ChessPieceFactory.create(PieceType.BISHOP_WHITE);
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("c8").get(), bb);
        pieces.put(CoordinatePair.of("f8").get(), bb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("a5").get(), pb);
        pieces.put(CoordinatePair.of("b5").get(), pb);
        pieces.put(CoordinatePair.of("c5").get(), pb);
        pieces.put(CoordinatePair.of("b4").get(), pb);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("c1").get(), bw);
        pieces.put(CoordinatePair.of("f1").get(), bw);
        pieces.put(CoordinatePair.of("h1").get(), rw);
        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(pieces));

        ScoreCounter scoreCounter = new ScoreCounter(board.getBoardState());
        assertThat(scoreCounter.getScore(Team.BLACK)).isEqualTo(19);
        assertThat(scoreCounter.getScore(Team.WHITE)).isEqualTo(16);
    }
}