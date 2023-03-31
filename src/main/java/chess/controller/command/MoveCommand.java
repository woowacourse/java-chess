package chess.controller.command;

import chess.controller.GameController;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements GameController {

    public static final int START_SOURCE_INDEX_IN_COMMANDLINE = 1;
    public static final int TARGET_SOURCE_INDEX_IN_COMMANDLINE = 2;
    private final String startPosition;
    private final String endPosition;

    private MoveCommand(String startPosition, String endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
    public static MoveCommand of(List<String> commandLine) {
        validateMoveCommandLine(commandLine);
        String startPosition = commandLine.get(START_SOURCE_INDEX_IN_COMMANDLINE);
        String endPosition = commandLine.get(TARGET_SOURCE_INDEX_IN_COMMANDLINE);
        return new MoveCommand(startPosition, endPosition);
    }

    private static void validateMoveCommandLine(final List<String> commandLine) {
        if(commandLine.size()<3) {
            throw new IllegalArgumentException("이동 입력은 move 시작점 도착점 입니다");
        }
    }

    @Override
    public void execute(ChessGameService chessGameService, OutputView outputView) {
        chessGameService.move(startPosition, endPosition);
        outputView.printBoard(chessGameService.findChessBoard());
    }
}
