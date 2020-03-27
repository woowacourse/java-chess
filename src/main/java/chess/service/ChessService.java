package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.Player;
import chess.domain.score.Score;

import java.util.Map;

public class ChessService {

    public Board initialize(Board board) {
        return board.initialize();
    }

    public Board move(Board board, Position source, Position target) {
        return board.move(source, target);
    }

    public Map<Player, Score> calculateScore(Board board) {
        return board.calculateScore();
    }

    public boolean checkGameNotFinished(Board board) {
        return board.isNotFinished();
    }
}
