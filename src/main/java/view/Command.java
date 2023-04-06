package view;

import dao.BoardDao;
import dao.Movement;
import domain.Board;
import domain.Turn;
import domain.piece.Piece;
import domain.point.Point;
import domain.util.ScoreCalculator;
import exception.GameFinishedException;

import java.util.List;
import java.util.Objects;

public class Command {
    private final CommandType commandType;
    private final String value;

    public Command(String value) {
        this.commandType = CommandType.findByCommand(value);
        this.value = value;
    }

    public void execute(String id, Board board, BoardDao boardDao, Turn turn, OutputView outputView) {
        if (commandType.isStart()) {
            board.reset();
        }

        if (commandType.isMoving()) {
            String[] split = value.split(" ");
            Movement movement = new Movement(Point.fromSymbol(split[1]), Point.fromSymbol(split[2]));
            board.move(movement.getStartingPoint(), movement.getDestinationPoint(), turn);
            boardDao.updateMovement(id, movement);
        }

        if (commandType.isStatus()) {
            List<List<Piece>> currentStatus = board.findCurrentStatus();
            float blackScore = ScoreCalculator.calculate(currentStatus, Turn.BLACK);
            float whiteScore = ScoreCalculator.calculate(currentStatus, Turn.WHITE);
            outputView.printScoreStatus(blackScore, whiteScore);
        }

        if (commandType.isEnd()) {
            throw new GameFinishedException();
        }
    }

    public boolean isStarting() {
        return this.commandType == CommandType.START;
    }

    public boolean isEnding() {
        return this.commandType == CommandType.END;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return commandType == command.commandType && value.equals(command.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, value);
    }
}
