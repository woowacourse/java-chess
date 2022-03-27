package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChessGameController {
    private static final Pattern POSITION_PATTERN = Pattern.compile("[a-h][1-8]");

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();

        while (true) {
            String command = inputView.inputCommand();
            if ("start".equals(command)) {
                chessGame.start();
                outputView.printBoard(chessGame.getBoard().getValue());
            }
            if (command.startsWith("move")) {
                List<String> commands = Arrays.asList(command.split(" "));
                if (commands.size() != 3) {
                    throw new IllegalArgumentException("이동 명령을 형식에 맞게 입력하세요.");
                }
                List<Position> positions = commands.stream()
                        .filter(splitedCommand -> POSITION_PATTERN.matcher(splitedCommand).matches())
                        .map(Position::from)
                        .collect(Collectors.toList());
                chessGame.move(positions.get(0), positions.get(1));
                outputView.printBoard(chessGame.getBoard().getValue());
            }
            if ("end".equals(command)) {
                if (!chessGame.isRunning()) {
                    break;
                }
                chessGame.end();
            }
        }
    }
}
