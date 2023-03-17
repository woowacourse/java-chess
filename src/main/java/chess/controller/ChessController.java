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

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

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
        repeatReadStart();
        repeatPlay();
    }

    private void repeatPlay() {
        try {
            playGameUntilEnd();
        } catch (RuntimeException e) {
            outputView.printErrorMesage(e);
            outputView.printGuideMessage();
            repeatPlay();
        }
    }

    private void repeatReadStart() {
        try {
            startByCommand();
        } catch (RuntimeException e) {
            outputView.printErrorMesage(e);
            repeatReadStart();
        }
    }

    private void startByCommand() {
        List<String> command = inputView.inputCommand();
        if (Command.from(command.get(COMMAND_INDEX)) != Command.START) {
            throw new IllegalArgumentException("먼저 게임을 시작해야 합니다.");
        }
        List<Squares> board = chessGame.getBoard();
        printBoard(board);
    }

    private void printBoard(final List<Squares> board) {
        List<List<String>> collect = board.stream()
                .map(Squares::getPieces)
                .map(KindMapper::mapToStrings)
                .collect(Collectors.toList());

        Collections.reverse(collect);

        collect.forEach(outputView::printRank);
    }

    private void playGameUntilEnd() {
        List<String> command = inputView.inputCommand();
        while (Command.from(command.get(COMMAND_INDEX)) != Command.END) {
            playGame(command);
            command = inputView.inputCommand();
        }
    }

    private void playGame(List<String> command) {
        if (Command.from(command.get(COMMAND_INDEX)) == Command.MOVE) {
            chessGame.playTurn(PositionParser.parse(command.get(SOURCE_INDEX)), PositionParser.parse(command.get(TARGET_INDEX)));
            List<Squares> board = chessGame.getBoard();
            printBoard(board);
        }
    }
}
