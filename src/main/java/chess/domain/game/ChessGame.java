package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.game.state.ChessGameState;
import chess.domain.game.state.MovePiece;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private ChessGameState step;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.step = new MovePiece(new Turn(Color.WHITE));
    }

    public void movePiece(final PiecePosition source, final PiecePosition destination) {
        step = step.movePiece(chessBoard, source, destination);
    }

    public boolean playable() {
        return step.playable();
    }

    public void end() {
        step = step.end();
    }

    public Color winColor() {
        return step.winColor();
    }

    public List<Piece> pieces() {
        return chessBoard.pieces();
    }

    public Map<Color, Double> calculateScore() {
        return step.calculateScore(chessBoard);
    }

    public ChessBoard chessBoard() {
        return chessBoard;
    }
}
