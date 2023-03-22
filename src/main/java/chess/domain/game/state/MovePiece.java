package chess.domain.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class MovePiece implements ChessGameState {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public MovePiece(final ChessBoard chessBoard, final Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public boolean playable() {
        return true;
    }

    @Override
    public ChessGameState initialize() {
        throw new IllegalStateException("이미 시작된 상태입니다.");
    }

    @Override
    public ChessGameState movePiece(final PiecePosition source, final PiecePosition destination) {
        chessBoard.movePiece(turn, source, destination);

        return judgeState();
    }

    private ChessGameState judgeState() {
        if (chessBoard.existKingByColor(turn.enemyColor())) {
            return new MovePiece(chessBoard, turn.change());
        }
        return new EndGame(turn.color());
    }

    @Override
    public ChessGameState end() {
        return new EndGame(Color.NONE);
    }

    @Override
    public List<Piece> pieces() {
        return chessBoard.pieces();
    }

    @Override
    public Color winColor() {
        throw new IllegalArgumentException("아직 게임이 진행중입니다.");
    }

    @Override
    public Map<Color, Double> calculateScore() {
        return chessBoard.calculateScore();
    }
}
