package chess.domain;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChessGame {

    private final Board board;
    private Color turnColor = Color.WHITE;

    public ChessGame() {
        board = new Board(PieceFactory.createChessPieces());
    }

    public void movePiece(MoveCommand moveCommand) {
        board.validateMovement(turnColor, moveCommand);
        board.movePiece(moveCommand);
        changeTurn();
    }

    private void changeTurn() {
        turnColor = turnColor.next();
    }

    public boolean isRunning() {
        return board.isBothKingsAlive();
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.BLACK, Color.WHITE)
                .collect(Collectors.toMap(color -> color, board::calculateScoreOf));
    }

    public Map<Position, Piece> getBoard() {
        return board.getPieces();
    }
}
