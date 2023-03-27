package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.result.Score;
import chess.domain.side.Color;

import java.util.List;
import java.util.Map;

public class InitChessGame implements ChessGame {
    public static final String ERROR_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    @Override
    public ChessGame start() {
        Board board = BoardFactory.createInitial();
        return new RunningChessGame(board);
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
        return true;
    }
}
