package chess;

import chess.controller.ChessController;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum ChessCommand {

    START((chessController, param1) -> start(chessController)),
    MOVE(ChessCommand::move),
    STATUS((chessController, param1) -> status(chessController)),
    END((chessController, param1) -> end(chessController)),
    ;

    private static final int COMMAND_INDEX = 0;

    private final CommandRun<ChessController, List<String>> run;

    ChessCommand(CommandRun<ChessController, List<String>> run) {
        this.run = run;
    }

    public static ChessCommand findCommand(List<String> rawCommand) {
        return Arrays.stream(ChessCommand.values())
                .filter(chessCommand -> chessCommand.name().toLowerCase(Locale.ROOT).equals(rawCommand.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("잘못된 커맨드입니다. %s", rawCommand)));
    }

    public void accept(ChessController chessController, List<String> inputCommand) {
        run.execute(chessController, inputCommand);
    }

    private static void end(ChessController chessController) {
        chessController.finishGame();
    }

    private static void status(ChessController chessController) {
        ScoreDto scoreDto = chessController.score();
        OutputView.announceScoreDto(scoreDto);
    }

    private static void move(ChessController chessController, List<String> squares) {
        BoardDto boardDto = chessController.move(squares.get(1), squares.get(2));
        OutputView.announce(boardDto);
    }

    private static void start(ChessController chessController) {
        BoardDto boardDto = chessController.startGame();
        OutputView.announce(boardDto);
    }
}
