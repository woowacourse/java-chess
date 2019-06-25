package chess.domain.boardcell;

import chess.domain.ChessGame;
import chess.domain.CoordinatePair;
import chess.domain.LivingPieceGroup;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    ChessPiece rb = ChessPieceFactory.create(PieceType.ROOK_BLACK);
    ChessPiece bb = ChessPieceFactory.create(PieceType.BISHOP_BLACK);
    ChessPiece pb = ChessPieceFactory.create(PieceType.PAWN_BLACK);
    ChessPiece pw = ChessPieceFactory.create(PieceType.PAWN_WHITE);
    ChessPiece rw = ChessPieceFactory.create(PieceType.ROOK_WHITE);
    ChessPiece bw = ChessPieceFactory.create(PieceType.BISHOP_WHITE);

    @Test
    void getMovable() {
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("c8").get(), bb);
        pieces.put(CoordinatePair.of("f8").get(), bb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("b2").get(), pw);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("c1").get(), bw);
        pieces.put(CoordinatePair.of("f1").get(), bw);
        pieces.put(CoordinatePair.of("h1").get(), rw);
        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(pieces));

        assertThat(pw.getMovableCoordinates(board::getTeamAt, CoordinatePair.of("b2").get())).containsExactlyInAnyOrder(
            CoordinatePair.of("b3").get(),
            CoordinatePair.of("b4").get()
        );

    }

    @Test
    void frontIsNotEmpty() {
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("c8").get(), bb);
        pieces.put(CoordinatePair.of("f8").get(), bb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("b5").get(), pb);
        pieces.put(CoordinatePair.of("b4").get(), pw);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("c1").get(), bw);
        pieces.put(CoordinatePair.of("f1").get(), bw);
        pieces.put(CoordinatePair.of("h1").get(), rw);
        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(pieces));

        assertThat(pw.getMovableCoordinates(board::getTeamAt, CoordinatePair.of("b4").get())).isEmpty();
    }

    @Test
    void getMovable_enemy() {
        ChessPiece rb = ChessPieceFactory.create(PieceType.ROOK_BLACK);
        ChessPiece bb = ChessPieceFactory.create(PieceType.BISHOP_BLACK);
        ChessPiece pb = ChessPieceFactory.create(PieceType.PAWN_BLACK);
        ChessPiece pw = ChessPieceFactory.create(PieceType.PAWN_WHITE);
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
        pieces.put(CoordinatePair.of("b4").get(), pw);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("c1").get(), bw);
        pieces.put(CoordinatePair.of("f1").get(), bw);
        pieces.put(CoordinatePair.of("h1").get(), rw);
        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(pieces));

        assertThat(pw.getMovableCoordinates(board::getTeamAt, CoordinatePair.of("b4").get())).containsExactlyInAnyOrder(
            CoordinatePair.of("a5").get(),
            CoordinatePair.of("c5").get()
        );
    }
}