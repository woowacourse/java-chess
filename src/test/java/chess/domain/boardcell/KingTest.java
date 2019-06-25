package chess.domain.boardcell;

import chess.domain.ChessGame;
import chess.domain.CoordinatePair;
import chess.domain.LivingPieceGroup;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    void getMovable() {
        ChessPiece rb = CellFactory.create(PieceType.ROOK_BLACK);
        ChessPiece bb = CellFactory.create(PieceType.BISHOP_BLACK);
        ChessPiece pb = CellFactory.create(PieceType.PAWN_BLACK);
        ChessPiece pw = CellFactory.create(PieceType.PAWN_WHITE);
        ChessPiece rw = CellFactory.create(PieceType.ROOK_WHITE);
        ChessPiece bw = CellFactory.create(PieceType.BISHOP_WHITE);
        ChessPiece qw = CellFactory.create(PieceType.QUEEN_WHITE);
        ChessPiece kw = CellFactory.create(PieceType.KING_WHITE);
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("c8").get(), bb);
        pieces.put(CoordinatePair.of("f8").get(), bb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("d4").get(), pw);
        pieces.put(CoordinatePair.of("b3").get(), pb);
        pieces.put(CoordinatePair.of("f3").get(), pb);
        pieces.put(CoordinatePair.of("b2").get(), pw);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("c1").get(), bw);
        pieces.put(CoordinatePair.of("d1").get(), qw);
        pieces.put(CoordinatePair.of("e1").get(), kw);
        pieces.put(CoordinatePair.of("h1").get(), rw);
        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(pieces));
        assertThat(kw.getMovableCoordinates(board::getTeamAt, CoordinatePair.of("e1").get()))
            .containsExactlyInAnyOrder(
                CoordinatePair.of("d2").get(),
                CoordinatePair.of("e2").get(),
                CoordinatePair.of("f2").get(),
                CoordinatePair.of("f1").get()
            );
    }
}