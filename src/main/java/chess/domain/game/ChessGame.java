package chess.domain.game;

import chess.domain.piece.Team;

public class ChessGame {

    private final Board board;
    private Team turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = Team.getStartTeam();
    }

    public void progress(Position source, Position target) {
        if (board.isCorrectTeam(source, turn)) {
            board.move(source, target);
            turn = turn.reverse();
            return;
        }
        throw new IllegalArgumentException("[ERROR] 지금은 " + turn + "차례입니다.");
    }
}
