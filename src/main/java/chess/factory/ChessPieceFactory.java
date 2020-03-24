package chess.factory;

import chess.Team;
import chess.domain.chesspiece.*;

import java.util.ArrayList;
import java.util.List;

public class ChessPieceFactory {
    public static List<ChessPiece> create(Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new King(team));
        chessPieces.add(new Queen(team));
        chessPieces.add(new Rook(team));
        chessPieces.add(new Rook(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        chessPieces.add(new Pawn(team));
        return chessPieces;
    }
}
