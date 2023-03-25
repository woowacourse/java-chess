package chess.domain.chessgame.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Team;

public class Running implements GameState {
    private static final String RUNNING_STATE_EXCEPTION_MESSAGE = "게임을 실행 중일때 수행할 수 없는 동작입니다.";

    private final ChessBoard chessBoard;
    private Team currentTeam;

    public Running(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.currentTeam = Team.WHITE;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        if (chessBoard.isDifferentTeam(currentTeam, from)) {
            throw new IllegalArgumentException();
        }

        chessBoard.move(from, to);
        currentTeam = currentTeam.getNextTurn();
    }

    @Override
    public boolean isKingDead() {
        return chessBoard.isKingDead();
    }

    @Override
    public GameState close() {
        return new Ready();
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
