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
            Board board = Board.initBoard();
            OutputView.printChessBoard(board.getBoard());
            input = InputView.requestCommand();
            command = Command.of(input.get(0));
            while (!command.isEnd()) {
                if (command == Command.STATUS) {
                    OutputView.printStatus(board.getTeamScore(Team.WHITE), board.getTeamScore(Team.BLACK));
                    input = InputView.requestCommand();
                    command = Command.of(input.get(0));
                    continue;
                }
                if (board.isKingDead(board.movePiece(input.get(1), input.get(2), team))) {
                    OutputView.printFinishedGame(board.getBoard(), team);
                    break;
                }
                OutputView.printChessBoard(board.getBoard());
                input = InputView.requestCommand();
                command = Command.of(input.get(0));
                team = Team.switchTeam(team);
            }
        }
    }
}
