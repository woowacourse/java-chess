package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Ready;
import chess.dto.BoardDto;

import java.util.List;

public class ChessGame {
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    private final Turn turn;
    private Board board;
    private GameState state;

    public ChessGame(Board board) {
        this.board = board;
        this.state = new Ready(board);
        this.turn = new Turn();
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void status() {
        state = state.status();
    }

    public void move(List<String> arguments) {
        validateMoveArgument(arguments);
        state = state.move();

        Point source = Point.of(arguments.get(SOURCE_INDEX));
        Point destination = Point.of(arguments.get(DESTINATION_INDEX));
        move(source, destination, turn.now());
        turn.next();

        board = state.board();
    }

    private void move(Point source, Point destination, Team currentTeam) {
        validatePoint(source, destination);
        validateTurn(source, currentTeam);
        board.move(source, destination);
    }

    private void validatePoint(Point source, Point destination) {
        if (board.isEmpty(source)) {
            throw new IllegalArgumentException("움직일 수 있는 기물이 존재하지 않습니다.");
        }
        if (!board.canMove(source, destination)) {
            throw new IllegalArgumentException("해당 위치로는 움직일 수 없습니다.");
        }
    }

    private void validateTurn(Point source, Team currentTeam) {
        if (!board.isTeam(source, currentTeam)) {
            throw new IllegalArgumentException("현재 플레이어의 기물이 아닙니다.");
        }
    }

    private void validateMoveArgument(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new IllegalArgumentException("정확한 위치를 입력해주세요.");
        }
    }

    public boolean isRunning() {
        return board.isContinued();
    }

    public double score(Team team) {
        return board.score(team);
    }

    public BoardDto generateBoardDto() {
        return board.boardDto();
    }

    public GameState state() {
        return state;
    }
}
