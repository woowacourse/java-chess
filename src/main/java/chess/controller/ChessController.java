package chess.controller;

import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final String REGEX = " ";
    private final OutputView outputView = OutputView.getInstance();
    private final ChessGame chessGame;


    public ChessController() {
        chessGame = new ChessGame();
    }

    public void start() {
        System.out.println("hi");
        chessGame.start();
        outputView.printBoard(BoardDto.from(chessGame.getBoard()));

        while (!chessGame.isEnded()) {
            List<String> commands = getCommand();
            ChessExecution chessExecution = ChessExecution.from(commands.get(0));
            chessExecution.run(chessGame, commands);
        }
    }

    private List<String> getCommand() {
        InputView inputView = InputView.getInstance();
        return List.of(inputView.scanCommand().split(REGEX));
    }
}
