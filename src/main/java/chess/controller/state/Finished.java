package chess.controller.state;

import chess.domain.board.Board;
import chess.dto.ScoreDto;

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
    public ScoreDto status() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public ChessGameState end() {
        return alertFinished();
    }

    @Override
    public Turn getTurn() {
        return Turn.END;
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
