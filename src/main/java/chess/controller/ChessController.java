package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Squares;
import chess.view.Command;
import chess.view.InputView;
import chess.view.KindMapper;
import chess.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void play() {
        outputView.startMessage();
        List<String> command = inputView.inputStartCommand();
        System.out.println(command);
        System.out.println(Command.from(command.get(0)));
        if (Command.from(command.get(0)) == Command.START) {
            List<Squares> board = chessGame.getBoard();
            printBoard(board);
        }
        while (Command.from(command.get(0)) != Command.END) {
            if (Command.from(command.get(0)) == Command.MOVE) {
                chessGame.playTurn(PositionParser.parse(command.get(1)), PositionParser.parse(command.get(2)));
                List<Squares> board = chessGame.getBoard();
                printBoard(board);
            }
            command = inputView.inputStartCommand();
        }
    }

    private void printBoard(final List<Squares> board) {
        List<List<String>> collect = board.stream()
                .map(Squares::getPieces)
                .map(KindMapper::mapToStrings)
                .collect(Collectors.toList());

        Collections.reverse(collect);

        collect.forEach(outputView::printRank);
    }
}
