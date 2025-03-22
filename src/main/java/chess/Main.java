package chess;

import chess.piece.Piece;
import chess.position.Position;

public class Main {

    public static Position[][] positionBoard = Initializer.initializePositionBoard();
    public static Piece[][] pieceBoard = Initializer.initializePieceBoard();

    public static void main(String[] args) {

        Output.printBoard();
    }

}
