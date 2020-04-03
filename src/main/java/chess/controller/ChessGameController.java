package chess.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private static final String COMMAND_DELIMITER = " ";

    private ChessGame chessGame;
    private Map<String, Consumer<StringTokenizer>> commands;

    public ChessGameController(ChessGame chessGame) {
        this.chessGame = chessGame;
        this.commands = new HashMap<String, Consumer<StringTokenizer>>() {{
            put("start", tokenizer -> {
                chessGame.start();
                OutputView.printBoard(chessGame.board());
            });
            put("end", tokenizer -> chessGame.end());
            put("move", tokenizer -> {
                chessGame.move(Position.from(tokenizer.nextToken()),
                    Position.from(tokenizer.nextToken()));
                OutputView.printBoard(chessGame.board());
            });
            put("status", tokenizer -> OutputView.printStatus(chessGame.status()));
        }};
    }

    public void command(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, COMMAND_DELIMITER);
        commands.get(tokenizer.nextToken()).accept(tokenizer);
    }

    public void run() {
        OutputView.printGameStart();
        while (!chessGame.isFinished()) {
            command(InputView.inputCommand());
        }
    }
}
