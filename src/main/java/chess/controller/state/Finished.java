package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.dto.ScoreDto;
import javax.swing.JColorChooser;

public class Finished implements ChessGameState {

    private final Color winner;

    public Finished(Color winner) {
        this.winner = winner;
    }

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
        return this;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Color getWinner() {
        return winner;
    }

    @Override
    public boolean isEnded() {
        return true;
    }

    private Finished alertFinished() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

}
