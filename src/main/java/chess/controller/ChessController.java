package chess.controller;

import chess.Board;
import chess.Exception;
import chess.Turn;
import chess.command.Command;
import chess.command.Init;
import chess.controller.dto.PieceDto;
import chess.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {
    public void run() {
        Command command = startGame();
        Board board = Board.create(Pieces.createInit());
        command = playGame(command, board);
        OutputView.printFinishMessage();
        finishGame(command, board);
    }

    private Command startGame() {
        try {
            OutputView.startGame();
            String input = InputView.inputCommand();
            Command command = new Init(input);
            command = command.turnState(input);
            return command;
        } catch (IllegalArgumentException e) {
            Exception.printCommandException(e);
            return startGame();
        }

    }

    private Command playGame(Command command, Board board) {
        Turn turn = Turn.init();
        while (isRunning(command, board)) {
            turn = nextTurn(command, board, turn);
            OutputView.printBoard(convertToPieceDtos(board.getPieces()));
            command = turnState(command);
        }
        return command;
    }

    private boolean isRunning(Command command, Board board) {
        return !command.isEnd() && !board.isDeadKing();
    }

    private List<PieceDto> convertToPieceDtos(Pieces pieces) {
        return pieces.getPieces().stream()
                .sorted(Comparator.comparing(piece -> piece))
                .map(piece -> new PieceDto(piece.getName(), piece.isLastFile()))
                .collect(Collectors.toList());

    }

    private Turn nextTurn(Command command, Board board, Turn turn) {
        try {
            if (command.isMove()) {
                board.move(command.getCommandPosition(), turn);
                turn = turn.change();
            }
            return turn;
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            Exception.printTurnException(e);
            OutputView.printBoard(convertToPieceDtos(board.getPieces()));
            command = turnState(command);
            return nextTurn(command, board, turn);
        }
    }

    private void finishGame(Command command, Board board) {
        command = command.turnFinalState(InputView.inputCommand());
        if (command.isStatus()) {
            OutputView.printFinalResult(board.getWinTeam(), board.getWhiteTeamScore(), board.getBlackTeamScore());
        }
    }

    private Command turnState(Command command) {
        try {
            return command.turnState(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            Exception.printCommandException(e);
            return turnState(command);
        }
    }
}
