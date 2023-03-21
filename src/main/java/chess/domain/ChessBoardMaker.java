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
        makeNobility(board);
        makePawn(board);
        makeEmpty(board);
        return new ChessBoard(board);
    }

    private static void makeNobility(Map<Position, Piece> board) {
        setUpNobilityPiece(board, Rank.EIGHT, Camp.BLACK);
        setUpNobilityPiece(board, Rank.ONE, Camp.WHITE);
    }

    private static void makePawn(Map<Position, Piece> board) {
        setUpPawn(board, Rank.SEVEN, Camp.BLACK);
        setUpPawn(board, Rank.TWO, Camp.WHITE);
    }

    private static void makeEmpty(Map<Position, Piece> board) {
        setUpBlank(board, Rank.SIX);
        setUpBlank(board, Rank.FIVE);
        setUpBlank(board, Rank.FOUR);
        setUpBlank(board, Rank.THREE);
    }

    private static void setUpNobilityPiece(Map<Position, Piece> board, Rank rank, Camp camp) {
        board.put(Position.of(File.A, rank), new Rook(camp));
        board.put(Position.of(File.B, rank), new Knight(camp));
        board.put(Position.of(File.C, rank), new Bishop(camp));
        board.put(Position.of(File.D, rank), new Queen(camp));
        board.put(Position.of(File.E, rank), new King(camp));
        board.put(Position.of(File.F, rank), new Bishop(camp));
        board.put(Position.of(File.G, rank), new Knight(camp));
        board.put(Position.of(File.H, rank), new Rook(camp));
    }

    private static void setUpPawn(Map<Position, Piece> board, Rank rank, Camp camp) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Pawn(camp));
        }
    }

    private static void setUpBlank(Map<Position, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Empty());
        }
    }
}
