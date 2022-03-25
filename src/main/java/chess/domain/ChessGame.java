package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChessGame {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;

    public ChessGame() {
        board = initializeBoard();
    }

    private Board initializeBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        for (PositionY positionY : PositionY.values()) {
            fillRankWithBlank(pieces, positionY);
        }

        for (InitialPiece piece : InitialPiece.values()) {
            pieces.replace(piece.getPosition(), piece.piece());
        }

        return new Board(pieces);
    }

    private void fillRankWithBlank(Map<Position, Piece> pieces, PositionY positionY) {
        for (PositionX positionX : PositionX.values()) {
            Position position = new Position(positionX, positionY);
            pieces.put(position, new Blank());
        }
    }

    public void movePiece(String sourceCommand, String targetCommand) {
        Position source = parseToPosition(sourceCommand);
        Position target = parseToPosition(targetCommand);

        if(board.isCastable(currentTurnColor, source, target)){
            board.castle(source, target);
            changeTurn();
            return;
        }

        board.validateMovement(currentTurnColor, source, target);
        board.movePiece(source, target);

        if(board.isPromotable(target)){
            board.promoteTo(target, new Queen(currentTurnColor));
        }
        changeTurn();
    }

    private Position parseToPosition(String command) {
        return new Position(PositionX.of(command.substring(0, 1)), PositionY.of(command.substring(1)));
    }

    private void changeTurn() {
        currentTurnColor = currentTurnColor.nextTurnColor();
    }

    public boolean isRunning() {
        return board.isBothKingsAlive();
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.BLACK, Color.WHITE)
                .collect(Collectors.toMap(color -> color, board::calculateScoreOf));
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
