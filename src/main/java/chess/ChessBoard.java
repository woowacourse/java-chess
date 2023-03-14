package chess;

import chess.piece.Bishop;
import chess.piece.ChessPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final Map<Position, ChessPiece> board = new HashMap<>();

    static {
        Pawn.initialPosition(Team.BLACK)
            .forEach(position -> board.put(position, new Pawn(Team.BLACK)));
        Pawn.initialPosition(Team.WHITE)
            .forEach(position -> board.put(position, new Pawn(Team.WHITE)));
        Rook.initialPosition(Team.BLACK)
            .forEach(position -> board.put(position, new Rook(Team.BLACK)));
        Rook.initialPosition(Team.WHITE)
            .forEach(position -> board.put(position, new Rook(Team.WHITE)));
        Knight.initialPosition(Team.BLACK)
              .forEach(position -> board.put(position, new Knight(Team.BLACK)));
        Knight.initialPosition(Team.WHITE)
            .forEach(position -> board.put(position, new Knight(Team.WHITE)));
        Bishop.initialPosition(Team.BLACK)
              .forEach(position -> board.put(position, new Bishop(Team.BLACK)));
        Bishop.initialPosition(Team.WHITE)
            .forEach(position -> board.put(position, new Bishop(Team.WHITE)));
        Queen.initialPosition(Team.BLACK)
             .forEach(position -> board.put(position, new Queen(Team.BLACK)));
        Queen.initialPosition(Team.WHITE)
              .forEach(position -> board.put(position, new Queen(Team.WHITE)));
        King.initialPosition(Team.BLACK)
              .forEach(position -> board.put(position, new King(Team.BLACK)));
        King.initialPosition(Team.WHITE)
            .forEach(position -> board.put(position, new King(Team.WHITE)));
    }
}
