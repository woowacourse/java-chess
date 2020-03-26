package chess.factory;

import chess.domain.board.Row;
import chess.domain.chesspiece.*;
import chess.domain.game.Team;

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
            chessPieces.add(new Rook(Team.BLACK));
            chessPieces.add(new Knight(Team.BLACK));
            chessPieces.add(new Bishop(Team.BLACK));
            chessPieces.add(new Queen(Team.BLACK));
            chessPieces.add(new King(Team.BLACK));
            chessPieces.add(new Bishop(Team.BLACK));
            chessPieces.add(new Knight(Team.BLACK));
            chessPieces.add(new Rook(Team.BLACK));
            return new Row(chessPieces);
        }
        if (i == 7) {
            for (char col = 'a'; col <= 'h'; col++) {
                chessPieces.add(new Pawn(Team.BLACK));
            }
            return new Row(chessPieces);
        }
        if (i == 2) {
            for (char col = 'a'; col <= 'h'; col++) {
                chessPieces.add(new Pawn(Team.WHITE));
            }
            return new Row(chessPieces);
        }
        if (i == 1) {
            chessPieces.add(new Rook(Team.WHITE));
            chessPieces.add(new Knight(Team.WHITE));
            chessPieces.add(new Bishop(Team.WHITE));
            chessPieces.add(new Queen(Team.WHITE));
            chessPieces.add(new King(Team.WHITE));
            chessPieces.add(new Bishop(Team.WHITE));
            chessPieces.add(new Knight(Team.WHITE));
            chessPieces.add(new Rook(Team.WHITE));
            return new Row(chessPieces);
        }

        for (char col = 'a'; col <= 'h'; col++) {
            chessPieces.add(new Blank(Team.BLANK));
        }
        return new Row(chessPieces);
    }

}
