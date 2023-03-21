package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class ChessGame {

    private ChessGameStep step;

    public ChessGame(final ChessBoard chessBoard) {
        this.step = new InitializeGame(chessBoard);
    }

    public void initialize() {
        step = step.initialize();
    }

    public void movePiece(final PiecePosition source, final PiecePosition destination) {
        step = step.movePiece(source, destination);
    }

    public List<Piece> pieces() {
        return step.pieces();
    }

    public boolean playable() {
        return step.playable();
    }

    public void end() {
        step = step.end();
    }
}
