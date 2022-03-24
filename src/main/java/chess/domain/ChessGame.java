package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public final class ChessGame {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;

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

    public Grid[][] getBoard() {
        return board.getBoard();
    }

    public void movePiece(String source, String target) {
        Position sourcePosition = new Position(PositionX.of(source.substring(0, 1)), PositionY.of(source.substring(1)));
        Position targetPosition = new Position(PositionX.of(target.substring(0, 1)), PositionY.of(target.substring(1)));
        board.movePiece(currentTurnColor, sourcePosition, targetPosition);
    }
}
