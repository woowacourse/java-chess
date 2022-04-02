package chess.controller;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int COMMAND_INDEX = 0;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final Map<Character, Integer> convertColumn = new HashMap<>();
    private static final String ONLY_START_OR_END_COMMAND_EXCEPTION = "[ERROR] start, end 명령 중 하나를 입력해주세요";

    static {
        convertColumn.put('a', 1);
        convertColumn.put('b', 2);
        convertColumn.put('c', 3);
        convertColumn.put('d', 4);
        convertColumn.put('e', 5);
        convertColumn.put('f', 6);
        convertColumn.put('g', 7);
        convertColumn.put('h', 8);
    }

    public void run() {
        OutputView.printChessGameStart();

        while (isCommandStart(InputView.requestPlay())) {
            playChessGame();
        }
    }

    private boolean isCommandStart(String command) {
        validateStartOrEndCommand(command);
        return Command.of(command).equals(Command.START);
    }

    private void validateStartOrEndCommand(String command) {
        if (Command.of(command) != Command.START && Command.of(command) != Command.END) {
            throw new IllegalArgumentException(ONLY_START_OR_END_COMMAND_EXCEPTION);
        }
    }

    private void playChessGame() {
        ChessGame chessGame = ChessGame.create();
        sendDataForPrintCurrentChessBoard(chessGame);

        while (!chessGame.isGameEnd()) {
            doTurn(chessGame);
            sendDataForPrintCurrentChessBoard(chessGame);
        }
    }

    private void sendDataForPrintCurrentChessBoard(ChessGame chessGame) {
        OutputView.printCurrentChessBoard(ChessBoardDto.of(chessGame.getChessBoardInformation()));
    }

    private void doTurn(ChessGame chessGame) {
        List<String> command = InputView.requestMoveOrStatus();
        inGameCommand(chessGame, command);
    }

    private void inGameCommand(ChessGame chessGame, List<String> command) {
        if (Command.of(command.get(COMMAND_INDEX)).equals(Command.STATUS)) {
            statusCommand(chessGame);
        }
        if (Command.of(command.get(COMMAND_INDEX)).equals(Command.MOVE)) {
            moveCommand(chessGame, command);
        }
    }

    private void statusCommand(ChessGame chessGame) {
        OutputView.printStatus(chessGame.getStatusInformation());
    }

    private void moveCommand(ChessGame chessGame, List<String> command) {
        ChessBoardPosition sourcePosition = ChessBoardPosition.of(extractColumn(command.get(SOURCE_POSITION_INDEX))
                , extractRow(command.get(SOURCE_POSITION_INDEX)));
        ChessBoardPosition targetPosition = ChessBoardPosition.of(extractColumn(command.get(TARGET_POSITION_INDEX))
                , extractRow(command.get(TARGET_POSITION_INDEX)));
        chessGame.move(sourcePosition, targetPosition);
        sendDataForPrintCurrentChessBoard(chessGame);
    }

    private int extractColumn(String input) {
        return convertColumn.get(input.charAt(COLUMN_INDEX));
    }

    private int extractRow(String input) {
        return Character.getNumericValue(input.charAt(ROW_INDEX));
    }
}
