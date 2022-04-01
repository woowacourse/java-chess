package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.boardstrategy.BoardStrategy;
import chess.domain.piece.attribute.Team;
import java.util.Map;

public final class ChessGame {
    private static final String NO_TURN_MESSAGE = "현재 진영에 속해있지 않는 위치입니다.";

    private final Board board;
    private boolean isPlaying = true;
    private Team turn = Team.WHITE;

    public ChessGame(Board board) {
        this.board = board;
    }

    public ChessGame(BoardStrategy boardStrategy) {
        this.board = new Board(boardStrategy.create());
    }

    public void play(Position from, Position to) {
        validateTurn(from);
        boolean isCheckmate = isCheckmate(to);
        board.move(from, to);
        if (isCheckmate) {
            isPlaying = false;
            return;
        }
        turn = turn.changeTeam();
    }

    private void validateTurn(Position from) {
        if (!isTurn(from)) {
            throw new IllegalArgumentException(NO_TURN_MESSAGE);
        }
    }

    private boolean isCheckmate(Position to) {
        return board.isCheckmate(to);
    }

    private boolean isTurn(Position position) {
        return board.isSameColor(position, turn);
    }

    public Map<Team, Double> getScoreOfTeams() {
        return board.getScoreOfTeams();
    }

    public Team getWinner() {
        if (isPlaying) {
            return getWinnerByScore();
        }
        return turn;
    }

    private Team getWinnerByScore() {
        if (getScoreOfTeams().get(Team.BLACK) > getScoreOfTeams().get(Team.WHITE)) {
            return Team.BLACK;
        }
        if (getScoreOfTeams().get(Team.BLACK) < getScoreOfTeams().get(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.NONE;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Board getBoard() {
        return board;
    }
}
