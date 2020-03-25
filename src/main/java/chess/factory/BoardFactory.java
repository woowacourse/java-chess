package chess.factory;

import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;
import chess.domain.chesspiece.*;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    public static List<Row> createBoard() {
        List<Row> board = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            board.add(createRow(i));
        }
        return board;
    }


    public static Row createRow(int i) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        if (i == 8) {
            chessPieces.add(new Rook(new Position(8, 'a'), Team.BLACK));
            chessPieces.add(new Knight(new Position(8, 'b'), Team.BLACK));
            chessPieces.add(new Bishop(new Position(8, 'f'), Team.BLACK));
            chessPieces.add(new Queen(new Position(8, 'd'), Team.BLACK));
            chessPieces.add(new King(new Position(8, 'e'), Team.BLACK));
            chessPieces.add(new Bishop(new Position(8, 'c'), Team.BLACK));
            chessPieces.add(new Knight(new Position(8, 'g'), Team.BLACK));
            chessPieces.add(new Rook(new Position(8, 'h'), Team.BLACK));
            return new Row(chessPieces);
        }
        if (i == 7) {
            for (char col = 'a'; col <= 'h'; col++) {
                chessPieces.add(new Pawn(new Position(7, col), Team.BLACK));
            }
            return new Row(chessPieces);
        }
        if (i == 2) {
            for (char col = 'a'; col <= 'h'; col++) {
                chessPieces.add(new Pawn(new Position(2, col), Team.WHITE));
            }
            return new Row(chessPieces);
        }
        if (i == 1) {
            chessPieces.add(new Rook(new Position(1, 'a'), Team.WHITE));
            chessPieces.add(new Knight(new Position(1, 'b'), Team.WHITE));
            chessPieces.add(new Bishop(new Position(1, 'f'), Team.WHITE));
            chessPieces.add(new Queen(new Position(1, 'd'), Team.WHITE));
            chessPieces.add(new King(new Position(1, 'e'), Team.WHITE));
            chessPieces.add(new Bishop(new Position(1, 'c'), Team.WHITE));
            chessPieces.add(new Knight(new Position(1, 'g'), Team.WHITE));
            chessPieces.add(new Rook(new Position(1, 'h'), Team.WHITE));
            return new Row(chessPieces);
        }

        for (char col = 'a'; col <= 'h'; col++) {
            chessPieces.add(new Blank(new Position(i, col), Team.BLANK));
        }
        return new Row(chessPieces);
    }

}
