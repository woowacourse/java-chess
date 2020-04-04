package chess.domain.game;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private Board board;
    private Turn turn;

    public ChessGame(final Board board, final Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public void move(MoveParameter moveParameter) {
        board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
        turn.switchTurn();
    }

    public Map<Position, PieceState> getBoard() {
        return board.getBoard();
    }

    public Map<Team, Double> getStatus() {
        return Arrays.stream(Team.values())
                .collect(Collectors.toMap(
                        value -> value,
                        value -> board.getScores(value)
                ));
    }

    public double getScores(Team team) {
        return board.getScores(team);
    }

    public Team getTurn() {
        return turn.getTurn();
    }
}
