package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.Player;
import chess.domain.score.Score;

import java.util.Map;

public class ChessService {

    private Board board;

    public ChessService() {
        board = Board.createEmpty();
    }

    public Board initialize() {
        board = board.initialize();
        return board;
    }

    public Board move(Position source, Position target) {
        board = board.move(source, target);
        return board;
    }

    public Map<Player, Score> calculateScore() {
        return board.calculateScore();
    }

    public boolean checkGameNotFinished() {
        return board.isNotFinished();
    }
}
