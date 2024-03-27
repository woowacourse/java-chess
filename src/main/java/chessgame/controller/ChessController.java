package chessgame.controller;

import static chessgame.view.ChessCommand.*;

import chessgame.dto.RouteDto;
import chessgame.view.InputView;
import chessgame.domain.ChessBoard;
import java.util.Map;
import java.util.function.Consumer;
import chessgame.view.ChessCommand;
import chessgame.view.OutputView;

public class ChessController {

    private final Map<ChessCommand, Consumer<ChessBoard>> commandPerformances
            = Map.of(START, ChessBoard::reset, MOVE, chessBoard -> chessBoard.move(askRoute()));

    public void start() {
        var chessBoard = new ChessBoard();
        var chessCommand = ChessCommand.PENDING;
        while (chessCommand != END) {
            chessCommand = ExceptionHandler.handleInputWithRetry(() -> proceed(chessBoard));
            OutputView.printChessBoard(chessBoard.toDto());
        }
    }

    private ChessCommand proceed(ChessBoard chessBoard) {
        ChessCommand chessCommand;
        chessCommand = InputView.inputChessCommand();
        commandPerformances.get(chessCommand).accept(chessBoard);
        return chessCommand;
    }

    public static RouteDto askRoute() {
        final var source = ExceptionHandler.handleInputWithRetry(InputView::inputChessPoint);
        final var destination = ExceptionHandler.handleInputWithRetry(InputView::inputChessPoint);
        return new RouteDto(source, destination);
    }

}
