package chess.domain;

import chess.domain.board.Board;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private final long id;
    private final Board board;
    private Team turn;

    private ChessGame(long id, Board board, Team turn) {
        this.id = id;
        this.board = board;
        this.turn = turn;
    }

    public static ChessGame createGame(long id) {
        Board initBoard = Board.init();
        return new ChessGame(id, initBoard, Team.getStartTeam());
    }

    public static ChessGame setting(long id, Board board, Team turn) {
        return new ChessGame(id, board, turn);
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

    public Optional<Team> findWinner(Map<Team, Score> scores) {
        Score whiteScore = scores.get(Team.WHITE);
        Score blackScore = scores.get(Team.BLACK);

        return getWinner(whiteScore, blackScore);
    }

    private Optional<Team> getWinner(Score whiteScore, Score blackScore) {
        int result = whiteScore.compareTo(blackScore);

        if (result == 1) {
            return Optional.of(Team.WHITE);
        }
        if (result == -1) {
            return Optional.of(Team.BLACK);
        }
        return Optional.empty();
    }

    public long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return turn;
    }
}
