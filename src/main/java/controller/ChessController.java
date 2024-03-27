package controller;

import static view.ChessCommand.*;

import domain.ChessBoard;
import dto.RouteDto;
import java.util.Map;
import java.util.function.Consumer;
import view.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final Map<ChessCommand, Consumer<ChessBoard>> commandPerformances
            = Map.of(START, ChessBoard::reset, MOVE, chessBoard -> chessBoard.move(askRoute()));

    public void start() {
        var chessBoard = new ChessBoard();
        var chessCommand = ChessCommand.PENDING;
        while (chessCommand != END) {
            chessCommand = InputView.inputChessCommand();
            commandPerformances.get(chessCommand).accept(chessBoard);
            OutputView.printChessBoard(chessBoard.toDto());
        }
    }

    public static RouteDto askRoute() {
        final var source = InputView.inputChessPoint();
        final var destination = InputView.inputChessPoint();
        return new RouteDto(source, destination);
    }

}
