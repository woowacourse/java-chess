package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.view.OutputView;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public void validateCommandRequest(CommandRequest commandRequest) {
        chessGame.validateCommand(commandRequest);
    }

    public AppStatus start(CommandRequest commandRequest) {
        // TODO 게임방 목록 출력
        // TODO 게임방 아이디를 입력받는다. 없는 아이디일 경우 새로 생성된다.
        chessGame.start(1);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        return AppStatus.RUNNING;
    }

    public AppStatus move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        if (chessGame.isOver()) {
            OutputView.printGameOverMessage();
        }
        return AppStatus.RUNNING;
    }

    public AppStatus end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    public AppStatus status(CommandRequest commandRequest) {
        GameResultResponse gameResult = chessGame.computeResult(commandRequest);
        OutputView.printGameResult(gameResult);
        return AppStatus.RUNNING;
    }

    public AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
