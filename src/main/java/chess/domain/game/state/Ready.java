package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.piece.Color;

public class Ready implements ChessGame {

    private static final String NOT_STARTED_GAME = "[ERROR] 게임이 시작되지 않았습니다.";

    public Ready() {
        Board board = BoardFactory.getEmptyBoard();
    }

    @Override
    public ChessGame initBoard() {
        return new Running(BoardFactory.getInitialPieces());
    }

    @Override
    public ChessGame movePiece(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(NOT_STARTED_GAME);
    }

    @Override
    public ChessGame end() {
        throw new IllegalStateException(NOT_STARTED_GAME);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public double calculateScore(Color color) {
        throw new IllegalStateException(NOT_STARTED_GAME);
    }

    @Override
    public Color judgeWinner() {
        throw new IllegalStateException(NOT_STARTED_GAME);
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException(NOT_STARTED_GAME);
    }
}