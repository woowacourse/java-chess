package chess.domain;

import chess.domain.command.MoveCommand;
import chess.domain.piece.AbstractPiece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public final class ChessGame {

    private final Board board;
    private Color turnColor = Color.WHITE;

    public ChessGame(Map<Position, AbstractPiece> pieces) {
        this.board = new Board(pieces);
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

    public Map<Color, Score> calculateScore() {
        Map<Color, Score> scores = new HashMap<>();
        for (Color color : Color.values()) {
            Map<Position, AbstractPiece> pieces = board.getPiecesOf(color);
            scores.put(color, Score.of(pieces));
        }
        return scores;
    }

    public Map<Position, AbstractPiece> getPieces() {
        return board.getPieces();
    }
}
