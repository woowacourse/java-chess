package chess.domain;

import chess.domain.board.Board;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Team turn;

    private ChessGame(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public static ChessGame createGame() {
        return new ChessGame(Board.init(), Team.getStartTeam());
    }

    public void movePiece(Position source, Position target) throws IllegalArgumentException {
        validateEmpty(source);
        validateTurn(source);
        board.move(source, target);
        turn = turn.reverse();
    }

    private void validateEmpty(Position source) {
        if (board.isEmptyPiece(source)) {
            throw new IllegalArgumentException("[ERROR] source 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Position source) {
        if (board.isNotTurn(source, turn)) {
            throw new IllegalArgumentException("[ERROR] 현재는 " + turn + "팀 차례입니다.");
        }
    }

    public boolean isOver() {
        return !board.hasKing(Team.WHITE) || !board.hasKing(Team.BLACK);
    }

    public Map<Team, Score> getScoreAllTeam() {
        Map<Team, Score> scores = new HashMap<>();

        scores.put(Team.WHITE, board.calculateScore(Team.WHITE));
        scores.put(Team.BLACK, board.calculateScore(Team.BLACK));

        return scores;
    }

    public Board getBoard() {
        return board;
    }
}
