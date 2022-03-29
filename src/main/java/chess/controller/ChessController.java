package chess.controller;

import chess.Board;
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
        OutputView.startGame();

        String input = InputView.inputCommand();
        Command command = new Init(input);
        command = command.turnState(input);
        Board board = Board.create(Pieces.createInit());
        command = playGame(command, board);
        OutputView.printFinishMessage();
        finishGame(command, board);
    }

    private Command playGame(Command command, Board board) {
        Turn turn = Turn.init();
        while (!command.isEnd() || board.isDeadKing()) {
            turn = nextTurn(command, board, turn);
            OutputView.printBoard(convertToPieceDtos(board.getPieces()));
            command = command.turnState(InputView.inputCommand());
        }
        return command;
    }

    public List<PieceDto> convertToPieceDtos(Pieces pieces) {
        return pieces.getPieces().stream()
                .sorted(Comparator.comparing(piece -> piece))
                .map(piece -> new PieceDto(piece.getName(), piece.isLastFile()))
                .collect(Collectors.toList());

    }

    private Turn nextTurn(Command command, Board board, Turn turn) {
        if (command.isMove()) {
            board.move(command.getCommandPosition(), turn);
            turn = turn.change();
        }
        return turn;
    }

    private void finishGame(Command command, Board board) {
        command = command.turnFinalState(InputView.inputCommand());
        if (command.isStatus()) {
            OutputView.printFinalResult(board.getWinTeam(), board.getWhiteTeamScore(), board.getBlackTeamScore());
        }
    }
}
