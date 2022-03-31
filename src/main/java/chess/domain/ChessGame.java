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

    public void proceedWith(MoveCommand moveCommand) {
        board.movePiece(turnColor, moveCommand);
        changeTurn();
    }

    private void changeTurn() {
        turnColor = turnColor.enemyColor();
    }

    public boolean isRunning() {
        return board.hasBothKings();
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.BLACK, Color.WHITE)
                .collect(Collectors.toMap(color -> color, board::calculateScoreOf));
    }

    public Map<Position, Piece> getBoard() {
        return board.getPieces();
    }
}
