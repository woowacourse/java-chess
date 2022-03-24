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
        Map<Position, Piece> pieces = new HashMap<>();

        for (PositionY positionY : PositionY.values()) {
            for (PositionX positionX : PositionX.values()) {
                Position position = new Position(positionX, positionY);
                pieces.put(position, new Blank());
            }
        }
        Arrays.stream(InitialPiece.values())
                .forEach(piece -> pieces.replace(piece.getPosition(), piece.piece()));

        return new Board(pieces);
    }

    public void movePiece(String sourceCommand, String targetCommand) {
        Position sourcePosition = parseToPosition(sourceCommand);
        Position targetPosition = parseToPosition(targetCommand);
        board.validateMovement(currentTurnColor, sourcePosition, targetPosition);
        board.movePiece(sourcePosition, targetPosition);
        changeTurn();
    }

    private Position parseToPosition(String command) {
        return new Position(PositionX.of(command.substring(0, 1)), PositionY.of(command.substring(1)));
    }

    private void changeTurn() {
        currentTurnColor = currentTurnColor.nextTurnColor();
    }

    public boolean isRunning() {
        return board.hasAliveBothKings();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
