package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.Arrays;
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

        Arrays.stream(PositionY.values())
                .forEach(positionY -> fillRankWithBlank(pieces, positionY));

        Arrays.stream(InitialPieces.values())
                .forEach(piece -> piece.addTo(pieces));

        return new Board(pieces);
    }

    private void fillRankWithBlank(Map<Position, Piece> pieces, PositionY positionY) {
        Arrays.stream(PositionX.values())
                .map(positionX -> new Position(positionX, positionY))
                .forEach(position -> pieces.put(position, new Blank()));
    }

    public void movePiece(String sourceCommand, String targetCommand) {
        Position source = parseToPosition(sourceCommand);
        Position target = parseToPosition(targetCommand);

        if (board.isCastable(currentTurnColor, source, target)) {
            board.castle(source, target);
            changeTurn();
            return;
        }

        if (board.isEnPassantAvailable(currentTurnColor, source, target)) {
            board.doEnPassant(currentTurnColor, source, target);
            changeTurn();
            return;
        }

        board.validateMovement(currentTurnColor, source, target);
        board.movePiece(source, target);
        checkPromotion(target);
        changeTurn();
    }

    private Position parseToPosition(String command) {
        return new Position(PositionX.of(command.substring(0, 1)), PositionY.of(command.substring(1)));
    }

    private void changeTurn() {
        currentTurnColor = currentTurnColor.nextTurnColor();
    }

    private void checkPromotion(Position target) {
        if (board.isPromotable(target)) {
            board.promoteTo(target, new Queen(currentTurnColor));
        }
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
