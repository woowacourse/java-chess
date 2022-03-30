package chess.controller;

import static chess.domain.Command.MOVE;
import static chess.domain.Command.STATUS;
import static chess.domain.Command.of;

import chess.domain.Board;
import chess.domain.Command;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void play() {
        OutputView.printStartMessage();
        List<String> input = InputView.requestCommand();
        Command command = of(input.get(InputView.COMMAND_INDEX));
        if (command.isStart()) {
            startGame();
        }
    }

    private void startGame() {
        Board board = Board.initBoard();
        OutputView.printChessBoard(board.getBoard());

        List<String> input = InputView.requestCommand();
        Command command = of(input.get(InputView.COMMAND_INDEX));

        playChessGame(input, command, board, Team.WHITE);
    }

    private void playChessGame(List<String> input, Command command, Board board, Team team) {
        while (!runByCommand(input, command, board, team)) {
            if (command == MOVE) {
                team = Team.switchTeam(team);
            }
            OutputView.printChessBoard(board.getBoard());
            input = InputView.requestCommand();
            command = of(input.get(InputView.COMMAND_INDEX));
        }
        OutputView.printFinishedGame(board.getBoard(), team);
    }

    private boolean runByCommand(List<String> input, Command command, Board board, Team team) {
        if (command == STATUS) {
            displayTeamStatus(board);
            return false;
        }
        if (command == MOVE) {
            return board.isKingDead(movePiece(input, board, team));
        }
        return false;
    }

    private void displayTeamStatus(Board board) {
        OutputView.printStatus(Score.calculateScore(board.getBoard(), Team.WHITE).getTotalScore(),
                Score.calculateScore(board.getBoard(), Team.BLACK).getTotalScore());
    }

    private Piece movePiece(List<String> input, Board board, Team team) {
        return board.movePiece(
                Position.from(input.get(InputView.FIRST_POSITION_INDEX)),
                Position.from(input.get(InputView.SECOND_POSITION_INDEX)),
                team);
    }
}
