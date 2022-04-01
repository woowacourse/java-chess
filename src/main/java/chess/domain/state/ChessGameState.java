package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;

public abstract class ChessGameState implements State {

    protected static final String UNSUPPORTED_SCORE = "score를 지원하지 않습니다.";
    protected static final String UNSUPPORTED_WINNER = "winner를 지원하지 않습니다.";
    protected static final String UNSUPPORTED_MOVE = "move를 지원하지 않습니다.";
    protected static final String UNSUPPORTED_START = "start를 지원하지 않습니다.";

    private final ChessBoard chessBoard;
    private final Color color;

    protected ChessGameState(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    @Override
    public ChessBoard chessBoard() {
        return chessBoard;
    }

    @Override
    public Color currentColor() {
        return color;
    }

    @Override
    public Color changeTurn() {
        return color.reverse();
    }
}

