package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public final class ChessGame {
    private final Board board;

    public ChessGame() {
        board = initializeBoard();
    }

    private Board initializeBoard() {
        Grid[][] initialBoard = new Grid[8][8];

        for (PositionY positionY : PositionY.values()) {
            for (PositionX positionX : PositionX.values()) {
                initialBoard[positionX.getCoordination()][positionY.getCoordination()] = new Grid(new Blank());
            }
        }

        for (InitialPiece piece : InitialPiece.values()) {
            PositionY rank = piece.positionY();
            PositionX column = piece.positionX();
            initialBoard[rank.getCoordination()][column.getCoordination()] = new Grid(piece.piece());
        }

        return new Board(initialBoard);
    }

    public Grid[][] getBoard(){
        return board.getBoard();
    }
}
