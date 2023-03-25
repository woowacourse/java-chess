package chess.domain.chessgame;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piecesfactory.PiecesFactory;

import java.util.List;
import java.util.Map;

public abstract class ChessGame {
    final Board board;
    final Color currentTurnColor;

    ChessGame(final Board board, final Color currentTurnColor) {
        this.board = board;
        this.currentTurnColor = currentTurnColor;
    }

    public static ChessGame from(final PiecesFactory piecesFactory) {
        return new InitialChessGame(Board.from(piecesFactory));
    }

    public abstract ChessGame start();

    public abstract ChessGame move(final Position currentPosition, final Position targetPosition);

    public abstract boolean isPlaying();

    public abstract boolean isGameOver();

    public abstract ChessGame end();

    public abstract Map<Color, Double> calculateScoreByColor();

    public abstract Color findScoreWinner();

    public abstract List<Piece> getPieces();
}
