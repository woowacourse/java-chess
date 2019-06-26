package chess.domain;

import chess.domain.boardcell.ChessPiece;
import chess.domain.boardcell.ChessPieceFactory;
import chess.domain.boardcell.PieceType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.boardcell.PieceType.ROOK_BLACK;
import static chess.domain.boardcell.PieceType.ROOK_WHITE;
import static org.assertj.core.api.Assertions.assertThat;


class ChessGameTest {

    @Test
    void initBoard() {
        Map<CoordinatePair, ChessPiece> pieces = new HashMap<>();
        ChessPiece rb = ChessPieceFactory.create(ROOK_BLACK);
        ChessPiece rw = ChessPieceFactory.create(ROOK_WHITE);
        pieces.put(CoordinatePair.of("a8").get(), rb);
        pieces.put(CoordinatePair.of("h8").get(), rb);
        pieces.put(CoordinatePair.of("a1").get(), rw);
        pieces.put(CoordinatePair.of("h1").get(), rw);

        Map<CoordinatePair, PieceType> livings1 = new ChessGame(() -> LivingPieceGroup.of(pieces)).getBoardState();
        Map<CoordinatePair, PieceType> livings2 = new ChessGame(() -> LivingPieceGroup.of(pieces)).getBoardState();

        assertThat(livings1).isEqualTo(livings2);
    }

    @Test
    void move() {
        Map<CoordinatePair, ChessPiece> piecesFrom = new HashMap<>();
        ChessPiece rb = ChessPieceFactory.create(ROOK_BLACK);
        ChessPiece rw = ChessPieceFactory.create(ROOK_WHITE);
        piecesFrom.put(CoordinatePair.of("a8").get(), rb);
        piecesFrom.put(CoordinatePair.of("h8").get(), rb);
        piecesFrom.put(CoordinatePair.of("a1").get(), rw);
        piecesFrom.put(CoordinatePair.of("h1").get(), rw);

        Map<CoordinatePair, ChessPiece> piecesTo = new HashMap<>();
        piecesTo.put(CoordinatePair.of("a8").get(), rw);
        piecesTo.put(CoordinatePair.of("h8").get(), rb);
        piecesTo.put(CoordinatePair.of("h1").get(), rw);

        ChessGame board = new ChessGame(() -> LivingPieceGroup.of(piecesFrom));
        CoordinatePair from = CoordinatePair.of("a1").get();
        CoordinatePair to = CoordinatePair.of("a8").get();
        board.move(from, to);
        assertThat(board).isEqualTo(new ChessGame(() -> LivingPieceGroup.of(piecesTo)));
    }

}