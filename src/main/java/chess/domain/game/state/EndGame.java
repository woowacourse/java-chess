package chess.domain.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;

import java.util.Map;

public class EndGame implements ChessGameState {

    private final Color winColor;

    public EndGame(final Color winColor) {
        this.winColor = winColor;
    }

    @Override
    public boolean playable() {
        return false;
    }

    @Override
    public ChessGameState movePiece(final ChessBoard chessBoard, final PiecePosition source, final PiecePosition destination) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public ChessGameState end() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public Color winColor() {
        return winColor;
    }

    @Override
    public Map<Color, Double> calculateScore(final ChessBoard chessBoard) {
        throw new IllegalArgumentException("이미 게임 끝났습니다.");
    }

    @Override
    public Turn turn() {
        throw new IllegalArgumentException("이미 게임 끝났습니다.");
    }
}
