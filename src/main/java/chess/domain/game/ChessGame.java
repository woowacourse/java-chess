package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.boardstrategy.BoardStrategy;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.piece.attribute.Team;
import chess.dto.CommandDto;
import chess.view.Command;
import java.util.Map;

public final class ChessGame {
    private static final String NO_TURN_MESSAGE = "현재 진영에 속해있지 않는 위치입니다.";
    private static final String INVALID_COMMEND_MESSAGE = "move 만 입력할 수 있습니다.";

    private Board board;
    private boolean isFinished = false;
    private Team turn = Team.WHITE;

    public ChessGame(Team turn, Board board) {
        this.turn = turn;
        this.board = board;
    }

    public ChessGame(BoardStrategy boardStrategy) {
        this.board = new Board(boardStrategy.create());
    }

    public void reset() {
        this.turn = Team.WHITE;
        this.isFinished = false;
        this.board = new Board(new InitBoardStrategy().create());
    }

    public void clone(ChessGame other) {
        this.turn = other.turn;
        this.isFinished = other.isFinished;
        this.board = other.board;
    }

    public void execute(CommandDto commandDto) {
        if (isFinished) {
            return;
        }
        if (commandDto.getCommand() == Command.MOVE) {
            play(commandDto.toSourcePosition(), commandDto.toTargetPosition());
            return;
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    public void play(Position from, Position to) {
        validateTurn(from);
        boolean isCheckmate = isCheckmate(to);
        board.move(from, to);
        if (isCheckmate) {
            isFinished = true;
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
        if (!isFinished) {
            return getWinnerByScore();
        }
        return getTurn();
    }

    public Team getTurn() {
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

    public boolean isFinished() {
        return isFinished;
    }

    public Board getBoard() {
        return board;
    }
}
