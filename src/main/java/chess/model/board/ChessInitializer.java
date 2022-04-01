package chess.model.board;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.pawn.Pawn;
import java.util.HashMap;
import java.util.Map;

public class ChessInitializer implements BoardInitializer {

    @Override
    public Map<Square, Piece> initPieces() {
        Map<Square, Piece> board = new HashMap<>();
        initBlack(board);
        initEmpty(board);
        initWhite(board);
        return board;
    }

    private void initBlack(Map<Square, Piece> board) {
        initBaseLine(board, Rank.EIGHT, Color.BLACK);
        initPawns(board, Rank.SEVEN, Color.BLACK);
    }

    private void initBaseLine(Map<Square, Piece> board, Rank rank, Color color) {
        board.putAll(initRoyalPieces(color, rank));
    }

    private Map<Square, Piece> initRoyalPieces(Color color, Rank rank) {
        return Map.of(
                Square.of(File.A, rank), new Rook(color),
                Square.of(File.B, rank), new Knight(color),
                Square.of(File.C, rank), new Bishop(color),
                Square.of(File.D, rank), new Queen(color),
                Square.of(File.E, rank), new King(color),
                Square.of(File.F, rank), new Bishop(color),
                Square.of(File.G, rank), new Knight(color),
                Square.of(File.H, rank), new Rook(color)
        );
    }

    private void initPawns(Map<Square, Piece> board, Rank rank, Color color) {
        for (File file : File.values()) {
            board.put(Square.of(file, rank), Pawn.of(color));
        }
    }

    private void initEmpty(Map<Square, Piece> board) {
        for (Rank rank : Rank.emptyBaseLine()) {
            initEmptiesInRank(board, rank);
        }
    }

    private void initEmptiesInRank(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(Square.of(file, rank), new Empty());
        }
    }

    private void initWhite(Map<Square, Piece> board) {
        initPawns(board, Rank.TWO, Color.WHITE);
        initBaseLine(board, Rank.ONE, Color.WHITE);
    }
}
