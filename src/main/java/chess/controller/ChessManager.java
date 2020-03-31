package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessRunner;
import chess.domain.piece.Team;

public class ChessManager {

    private ChessRunner chessRunner;
    private boolean runFlag;

    public ChessManager() {
        this.chessRunner = new ChessRunner();
        runFlag = true;
    }

    public void start() {
        chessRunner = new ChessRunner();
    }

    public void end() {
        runFlag = false;
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
        runFlag = stopGameIfWinnerExists();
    }

    private boolean stopGameIfWinnerExists() {
        return !chessRunner.findWinner().isPresent();
    }

    public boolean isPlaying() {
        return runFlag;
    }

    public BoardDto getBoard() {
        return new BoardDto(chessRunner.getBoard());
    }

    public ChessManager get() {
        return this;
    }

    public double calculateScore() {
        return chessRunner.calculateScore();
    }

    public Team getCurrentTeam() {
        return chessRunner.getCurrentTeam();
    }

    public Team getWinner() {
        return chessRunner.findWinner().get();
    }
}
