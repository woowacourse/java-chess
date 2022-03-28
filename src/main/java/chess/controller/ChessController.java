package chess.controller;

import chess.Command;
import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void play() {
        OutputView.printStartMessage();
        List<String> input = InputView.requestCommand();
        Command command = Command.of(input.get(0));
        if (command.isStart()) {
            startGame();
        }
    }

    private void startGame() {
        Board board = Board.initBoard();
        OutputView.printChessBoard(board.getBoard());

        List<String> input = InputView.requestCommand();
        Command command = Command.of(input.get(0));

        playChessGame(input, command, board, Team.WHITE);
    }

    private void playChessGame(List<String> input, Command command, Board board, Team team) {
        while (!runByCommand(input, command, board, team)) {
            if (command == Command.MOVE) {
                team = Team.switchTeam(team);
            }
            OutputView.printChessBoard(board.getBoard());
            input = InputView.requestCommand();
            command = Command.of(input.get(0));
        }
        OutputView.printFinishedGame(board.getBoard(), team);
    }

    private boolean runByCommand(List<String> input, Command command, Board board, Team team) {
        if (command == Command.STATUS) {
            displayTeamStatus(board);
            return false;
        }
        if (command == Command.MOVE) {
            return board.isKingDead(movePiece(input, board, team));
        }
        return false;
    }

    private void displayTeamStatus(Board board) {
        OutputView.printStatus(board.getTeamScore(Team.WHITE), board.getTeamScore(Team.BLACK));
    }

    private Piece movePiece(List<String> input, Board board, Team team) {
        return board.movePiece(input.get(1), input.get(2), team);
    }
}
