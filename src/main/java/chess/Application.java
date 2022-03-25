package chess;

import chess.domain.Board;
import chess.domain.Team;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView.printStartMessage();
        List<String> input = InputView.requestCommand();
        Command command = Command.of(input.get(0));
        Team team = Team.WHITE;
        if (command.isStart()) {
            Board board = new Board();
            OutputView.printChessBoard(board.getBoard());
            input = InputView.requestCommand();
            command = Command.of(input.get(0));
            while (!command.isEnd()) {
                board.movePiece(input.get(1), input.get(2), team);
                OutputView.printChessBoard(board.getBoard());
                input = InputView.requestCommand();
                command = Command.of(input.get(0));
                team = Team.switchTeam(team);
            }
        }
    }
}
