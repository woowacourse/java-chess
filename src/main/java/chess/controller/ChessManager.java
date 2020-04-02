package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;
import chess.domain.piece.Team;

public class ChessManager {

    private ChessRunner chessRunner;
    private boolean isPlaying;

    public ChessManager() {
        this.chessRunner = new ChessRunner();
        isPlaying = true;
    }

    public void start() {
        chessRunner = new ChessRunner();
    }

    public void end() {
        isPlaying = false;
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
        isPlaying = stopGameIfWinnerExists();
    }

    private boolean stopGameIfWinnerExists() {
        return !chessRunner.findWinner().isPresent();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public BoardDto getBoard() {
        return new BoardDto(chessRunner.getBoard());
    }

    public TileDto getTileDto() {
        return new TileDto(chessRunner.getBoard());
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
