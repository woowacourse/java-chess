package chess.controller.state;

import chess.domain.board.Board;

public class Finished implements ChessGameState {

    @Override
    public ChessGameState start() {
        return alertFinished();
    }

    @Override
    public ChessGameState move(String from, String to) {
        return alertFinished();
    }

    @Override
    public ChessGameState status() {
        return alertFinished();
    }

    @Override
    public ChessGameState end() {
        return alertFinished();
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public boolean isEnded() {
        return true;
    }

    private Finished alertFinished() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

}
