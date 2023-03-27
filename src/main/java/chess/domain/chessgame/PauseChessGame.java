package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.result.Score;
import chess.domain.side.Color;

import java.util.List;
import java.util.Map;

public class PauseChessGame implements ChessGame {
    public static final String ERROR_MESSAGE = "정지한 게임입니다.";

    private final Board board;

    public PauseChessGame(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame start() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public ChessGame pause() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public ChessGame move(String sourceSquareInput, String targetSquareInput) {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public Color findWinner() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public Map<Color, Score> status() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public List<List<Piece>> findChessBoard() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}
