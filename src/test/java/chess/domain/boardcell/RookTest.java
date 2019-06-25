package chess.domain.boardcell;

import chess.domain.ChessGame;
import chess.domain.CoordinatePair;
import chess.domain.LivingPieceGroup;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void getMovable() {
        ChessPiece rb = ChessPieceFactory.create(PieceType.ROOK_BLACK);
        ChessPiece rw = ChessPieceFactory.create(PieceType.ROOK_WHITE);
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("h1").get(), rw);

        CoordinatePair from = CoordinatePair.of("a1").get();

        ChessGame game = new ChessGame(() -> LivingPieceGroup.of(pieces));

        assertThat(rw.getMovableCoordinates(game::getTeamAt, from)).containsExactlyInAnyOrder(
            CoordinatePair.of("a2").get(),
            CoordinatePair.of("a3").get(),
            CoordinatePair.of("a4").get(),
            CoordinatePair.of("a5").get(),
            CoordinatePair.of("a6").get(),
            CoordinatePair.of("a7").get(),
            CoordinatePair.of("a8").get(),
            CoordinatePair.of("b1").get(),
            CoordinatePair.of("c1").get(),
            CoordinatePair.of("d1").get(),
            CoordinatePair.of("e1").get(),
            CoordinatePair.of("f1").get(),
            CoordinatePair.of("g1").get()
        );
    }

    @Test
    void ally_block() {
        ChessPiece rb = ChessPieceFactory.create(PieceType.ROOK_BLACK);
        ChessPiece rw = ChessPieceFactory.create(PieceType.ROOK_WHITE);
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("a4").get(), rw);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("d1").get(), rw);
        pieces.put(CoordinatePair.of("h1").get(), rw);

        CoordinatePair from = CoordinatePair.of("a1").get();
        ChessGame game = new ChessGame(() -> LivingPieceGroup.of(pieces));

        assertThat(rw.getMovableCoordinates(game::getTeamAt, from))
            .containsExactlyInAnyOrder(
                CoordinatePair.of("a2").get(),
                CoordinatePair.of("a3").get(),
                CoordinatePair.of("b1").get(),
                CoordinatePair.of("c1").get()
            );
    }
}