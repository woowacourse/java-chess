package chess.controller;

import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.game.ChessGame;
import chess.view.*;

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
        while (chessGame.isOnGoing()) {
            repeatRead();
        }
    }

    private void repeatRead() {
        try {
            List<String> commands = inputView.inputCommand();
            isMove(commands);
            isStart(commands);
            isEnd(commands);
        } catch (RuntimeException e) {
            outputView.printErrorMesage(e);
            outputView.printGuideMessage();
            repeatRead();
        }
    }

    private void isMove(final List<String> commands) {
        if (Command.from(commands.get(COMMAND_INDEX)) == Command.MOVE) {
            Position parsedFile = PositionParser.parse(commands.get(SOURCE_INDEX));
            Position parsedRank = PositionParser.parse(commands.get(TARGET_INDEX));
            chessGame.playTurn(parsedFile, parsedRank);
            printBoard(chessGame.getBoard());
        }
    }

    private void isStart(final List<String> commands) {
        if (Command.from(commands.get(COMMAND_INDEX)) == Command.START) {
            startGame();
        }
    }

    private void startGame() {
        chessGame.startGame();
        printBoard(chessGame.getBoard());
    }

    private void isEnd(final List<String> commands) {
        if (Command.from(commands.get(COMMAND_INDEX)) == Command.END) {
            chessGame.end();
        }
    }

    private void printBoard(final List<Squares> board) {
        List<List<String>> pieceNames = board.stream()
                .map(Squares::getPieces)
                .map(KindMapper::mapToStrings)
                .collect(Collectors.toList());
        Collections.reverse(pieceNames);

        pieceNames.forEach(outputView::printRank);
    }
}
