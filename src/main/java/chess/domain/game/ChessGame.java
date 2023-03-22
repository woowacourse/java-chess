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

    private Long id;
    private final ChessBoard chessBoard;
    private ChessGameState state;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.state = new MovePiece(new Turn(Color.WHITE));
    }

    public ChessGame(final Long id, final ChessBoard chessBoard, final ChessGameState state) {
        this.id = id;
        this.chessBoard = chessBoard;
        this.state = state;
    }

    public void movePiece(final PiecePosition source, final PiecePosition destination) {
        state = state.movePiece(chessBoard, source, destination);
    }

    public boolean playable() {
        return state.playable();
    }

    public Color winColor() {
        return state.winColor();
    }

    public List<Piece> pieces() {
        return chessBoard.pieces();
    }

    public Map<Color, Double> calculateScore() {
        return state.calculateScore(chessBoard);
    }

    public Long id() {
        return id;
    }

    public ChessGameState state() {
        return state;
    }

    public Color turnColor() {
        return state.turn().color();
    }
}
