package controller.chess;

import service.ChessGameService;
import view.OutputView;

import java.util.List;

public class MoveController extends GameController {

    private final List<String> commandInput;

    protected MoveController(ChessGameService chessGameService, List<String> commandInput) {
        super(chessGameService);
        this.commandInput = commandInput;
    }

    @Override
    public ChessController run() {
        executeMove();

        if (chessGameService.isGameEnded()) {
            return new GameEndController(chessGameService);
        }
        return readNextController();
    }

    private void executeMove() {
        try {
            validateCommandInputSize();
            movePiece();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void validateCommandInputSize() {
        if (commandInput.size() != 3) {
            throw new IllegalArgumentException("[ERROR] Move 명령어 입력이 적합한 형식이 아닙니다.");
        }
    }

    private void movePiece() {
        chessGameService.move(commandInput);
        OutputView.printChessBoardState(chessGameService.makeChessBoardState());
    }

    @Override
    public boolean isEndController() {
        return false;
    }
}
