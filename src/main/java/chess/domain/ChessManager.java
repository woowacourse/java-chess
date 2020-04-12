package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Team;

import java.util.Optional;

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

    public double calculateScore() {
        return chessRunner.calculateScore();
    }

    public Board getBoard() {
        return chessRunner.getBoard();
    }

    public Team getCurrentTeam() {
        return chessRunner.getCurrentTeam();
    }

    public Optional<Team> getWinner() {
        return chessRunner.findWinner();
    }
}
