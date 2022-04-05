package chess.domain;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public final class ChessGame {

    private final Board board;
    private PieceColor turnColor = PieceColor.WHITE;

    public ChessGame(Map<Position, Piece> pieces) {
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

    public Map<PieceColor, Score> calculateScoreByColor() {
        Map<PieceColor, Score> scores = new HashMap<>();
        for (PieceColor color : PieceColor.values()) {
            Map<Position, Piece> pieces = board.getPiecesOf(color);
            scores.put(color, Score.of(pieces));
        }
        return scores;
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }
}
