package chess.contoller;

import chess.domain.ChessGame;
import chess.domain.board.*;
import chess.domain.command.Command;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChessController {

    public void play() {
        ChessGame chessGame = new ChessGame(new Board());

        ready(chessGame);
        start(chessGame);
    }

    private void ready(ChessGame chessGame) {
        OutputView.printStartInfo();
        List<String> input = InputView.InputString();
        validateStartCommand(chessGame, input);
    }

    private void validateStartCommand(ChessGame chessGame, List<String> input) {
        try {
            Command command = Command.getByInput(input.get(0));
            validateStart(command);
            command.execute(chessGame, input);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            ready(chessGame);
        }
    }

    private void validateStart(Command command) {
        List<Command> possibleCommand = Arrays.asList(Command.START, Command.END);
        if (!possibleCommand.contains(command)) {
            throw new IllegalArgumentException("start, end 이외의 명령은 입력할 수 없습니다.");
        }
    }

    private void start(ChessGame chessGame) {
        while (chessGame.isRunning()) {
            OutputView.printChessBoard(boardDto(chessGame));
            List<String> input = InputView.InputString();
            validateCommand(chessGame, input);
        }
        OutputView.printGameOverInfo();
        OutputView.printMessage(chessGame.winner());
    }

    private void validateCommand(ChessGame chessGame, List<String> input) {
        try {
            Command command = Command.getByInput(input.get(0));
            command.execute(chessGame, input);
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            start(chessGame);
        }
    }

    public static void printScoreIfStatus(ChessGame chessGame) {
            OutputView.printTeamScore(chessGame.score(Team.WHITE), Team.WHITE);
            OutputView.printTeamScore(chessGame.score(Team.BLACK), Team.BLACK);
    }

    public BoardDto boardDto(ChessGame chessGame) {
        List<List<String>> board = new ArrayList<>();
        for (Row row : Row.reverseRows()) {
            board.add(rowLine(row, chessGame));
        }

        return new BoardDto(board);
    }

    private List<String> rowLine(Row row, ChessGame chessGame) {
        List<String> rowLine = new ArrayList<>();
        Map<Point, Square> board = chessGame.board();
        for (Column column : Column.columns()) {
            Square square = board.get(Point.of(column.xCoordinate() + row.yCoordinate()));
            rowLine.add(square.pieceName());
        }
        return rowLine;
    }
}
