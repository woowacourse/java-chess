package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ChessBoardMaker {

    public static ChessBoard create() {
        Map<Position, Piece> board = new LinkedHashMap<>();

        setUpNobilityPiece(board, Rank.EIGHT, Camp.BLACK);
        setUpPawn(board, Rank.SEVEN, Camp.BLACK);
        makeEmpty(board);
        setUpPawn(board, Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(board, Rank.ONE, Camp.WHITE);

        return new ChessBoard(board);
    }

    private static void setUpNobilityPiece(final Map<Position, Piece> board, final Rank rank, final Camp camp) {
        board.put(Position.of(File.A, rank), new Rook(camp));
        board.put(Position.of(File.B, rank), new Knight(camp));
        board.put(Position.of(File.C, rank), new Bishop(camp));
        board.put(Position.of(File.D, rank), new Queen(camp));
        board.put(Position.of(File.E, rank), new King(camp));
        board.put(Position.of(File.F, rank), new Bishop(camp));
        board.put(Position.of(File.G, rank), new Knight(camp));
        board.put(Position.of(File.H, rank), new Rook(camp));
    }

    private static void makeEmpty(final Map<Position, Piece> board) {
        setUpBlank(board, Rank.SIX);
        setUpBlank(board, Rank.FIVE);
        setUpBlank(board, Rank.FOUR);
        setUpBlank(board, Rank.THREE);
    }

    private static void setUpPawn(final Map<Position, Piece> board, final Rank rank, final Camp camp) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Pawn(camp));
        }
    }

    private static void setUpBlank(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Empty());
        }
    }
}
