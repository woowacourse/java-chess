package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;
import chess.domain.piece.Team;

public class ChessManager {

    private ChessRunner chessRunner;
    private boolean playing;

    public ChessManager() {
        this.chessRunner = new ChessRunner();
        playing = true;
    }

    public void start() {
        chessRunner = new ChessRunner();
    }

    public void end() {
        playing = false;
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
        playing = stopGameIfWinnerExists();
    }

    private boolean stopGameIfWinnerExists() {
        return !chessRunner.findWinner().isPresent();
    }

    public boolean isPlaying() {
        return playing;
    }

    public BoardDto getBoard() {
        return new BoardDto(chessRunner.getBoard());
    }

    public TileDto getTileDto() {
        return new TileDto(chessRunner.getBoard());
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
