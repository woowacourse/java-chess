package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.쭈;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;

public class Initializer {

    public static Piece[][] initializePieceBoard() {
        Piece[][] pieces = new Piece[8][8];

        pieces[0][0] = new Rook(Color.BLACK);
        pieces[0][1] = new Knight(Color.BLACK);
        pieces[0][2] = new Bishop(Color.BLACK);
        pieces[0][3] = new King(Color.BLACK);
        pieces[0][4] = new Queen(Color.BLACK);
        pieces[0][5] = new Bishop(Color.BLACK);
        pieces[0][6] = new Knight(Color.BLACK);
        pieces[0][7] = new Rook(Color.BLACK);

        pieces[1][0] = new 쭈(Color.BLACK);
        pieces[1][1] = new 쭈(Color.BLACK);
        pieces[1][2] = new 쭈(Color.BLACK);
        pieces[1][3] = new 쭈(Color.BLACK);
        pieces[1][4] = new 쭈(Color.BLACK);
        pieces[1][5] = new 쭈(Color.BLACK);
        pieces[1][6] = new 쭈(Color.BLACK);
        pieces[1][7] = new 쭈(Color.BLACK);

        pieces[6][0] = new 쭈(Color.WHITE);
        pieces[6][1] = new 쭈(Color.WHITE);
        pieces[6][2] = new 쭈(Color.WHITE);
        pieces[6][3] = new 쭈(Color.WHITE);
        pieces[6][4] = new 쭈(Color.WHITE);
        pieces[6][5] = new 쭈(Color.WHITE);
        pieces[6][6] = new 쭈(Color.WHITE);
        pieces[6][7] = new 쭈(Color.WHITE);

        pieces[7][0] = new Rook(Color.WHITE);
        pieces[7][1] = new Knight(Color.WHITE);
        pieces[7][2] = new Bishop(Color.WHITE);
        pieces[7][3] = new King(Color.WHITE);
        pieces[7][4] = new Queen(Color.WHITE);
        pieces[7][5] = new Bishop(Color.WHITE);
        pieces[7][6] = new Knight(Color.WHITE);
        pieces[7][7] = new Rook(Color.WHITE);

        return pieces;
    }


    // for debug
    public static Position[][] initializePositionBoard() {
        Position[][] positions = new Position[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                positions[i][j] = new Position(
                        Row.values()[i],
                        Column.values()[j]
                );
                System.out.println(Row.values()[i].y == i);
                System.out.println(Column.values()[j].x == j);
            }
        }
        return positions;
    }
}
