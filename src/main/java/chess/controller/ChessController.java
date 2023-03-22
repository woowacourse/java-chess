package chess.controller;

import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.game.ChessGame;
import chess.view.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;
    private final Map<Command, Action> commandMap = new HashMap<>();

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
        commandMap.put(Command.START, new Action(ignored -> start()));
        commandMap.put(Command.MOVE, new Action(this::move));
        commandMap.put(Command.END, new Action(ignored -> end()));
        commandMap.put(Command.SAVE, new Action(ignored -> save()));
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
            Command command = Command.from(commands.get(COMMAND_INDEX));
            commandMap.get(command).excute(commands);
        } catch (RuntimeException e) {
            outputView.printErrorMesage(e);
            outputView.printGuideMessage();
            repeatRead();
        }
    }

    private void start() {
        chessGame.startGame();
        printBoard(chessGame.getBoard());
    }

    private void printBoard(final List<Squares> board) {
        List<List<String>> pieceNames = board.stream()
                .map(Squares::getPieces)
                .map(KindMapper::mapToStrings)
                .collect(Collectors.toList());

        Collections.reverse(pieceNames);

        pieceNames.forEach(outputView::printRank);
    }

    private void move(final List<String> commands) {
        Position parsedFile = PositionParser.parse(commands.get(SOURCE_INDEX));
        Position parsedRank = PositionParser.parse(commands.get(TARGET_INDEX));
        chessGame.playTurn(parsedFile, parsedRank);
        printBoard(chessGame.getBoard());
    }


    private void save() {
        chessGame.save();
    }

    private void end() {
        chessGame.end();
    }
}
