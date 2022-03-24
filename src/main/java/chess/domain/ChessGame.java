package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class ChessGame {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;

    public ChessGame() {
        board = initializeBoard();
    }

    private Board initializeBoard() {
        Map<Position, Grid> initialBoard = new HashMap<>();

        for (PositionY positionY : PositionY.values()) {
            for (PositionX positionX : PositionX.values()) {
                Position position = new Position(positionX, positionY);
                initialBoard.put(position, new Grid(new Blank()));
            }
        }
        Arrays.stream(InitialPiece.values())
                .forEach(piece -> initialBoard.replace(piece.getPosition(), new Grid(piece.piece())));

        return new Board(initialBoard);
    }

    public void movePiece(String source, String target) {
        Position sourcePosition = new Position(PositionX.of(source.substring(0, 1)), PositionY.of(source.substring(1)));
        Position targetPosition = new Position(PositionX.of(target.substring(0, 1)), PositionY.of(target.substring(1)));
        board.movePiece(currentTurnColor, sourcePosition, targetPosition);
        changeTurn();
    }

    private void changeTurn() {
        currentTurnColor = currentTurnColor.nextTurnColor();
    }

    public boolean isRunning() {
        return true;
    }

    public Map<Position, Grid> getBoard() {
        return board.getBoard();
    }
}
