package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Blank;
import chess.domain.piece.InitialPiece;
import chess.domain.piece.Piece;
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

    public void movePiece(MoveCommand moveCommand) {
        board.validateMovement(currentTurnColor, moveCommand);
        board.movePiece(moveCommand);
        changeTurn();
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
