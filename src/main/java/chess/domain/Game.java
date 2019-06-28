package chess.domain;

import java.util.Objects;

public class Game {
    private Round round;
    private Board board;

    public Game() {
        round = new Round(0);
        this.board = BoardFactory.create();
    }

    public Game(Round round, Board board) {
        this.round = round;
        this.board = board;
    }

    public Game play(int from, int to) {
        Spot startSpot = Spot.valueOf(from);
        Spot endSpot = Spot.valueOf(to);

        if (!board.checkTeam(startSpot, round.getTeam())) {
            return this;
        }

        Board movedBoard = board.move(startSpot, endSpot);
        if (!movedBoard.equals(board)) {
            return new Game(round.nextRound(), movedBoard);
        }

        return this;
    }

    public Game reload(int start, int end) {
        Spot from = Spot.valueOf(start);
        Spot to = Spot.valueOf(end);

        return new Game(round.nextRound(), board.move(from, to));
    }

    public StatusBoard getStatusBoard() {
        return StatusBoardFactory.create(board);
    }

    public int getRound() {
        return round.getRound();
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(round, game.round) &&
                Objects.equals(board, game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, board);
    }
}
