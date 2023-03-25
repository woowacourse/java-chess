package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.view.OutputView;

public class ChessController {

    private ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public AppStatus start(CommandRequest commandRequest) {
        chessGame.start(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        return AppStatus.RUNNING;
    }

    public AppStatus move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        if (chessGame.isOver()) {
            // TODO 메시지 출력
        }
        return AppStatus.RUNNING;
    }

    public AppStatus end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        chessGame = new ChessGame();
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    public AppStatus status(CommandRequest commandRequest) {
        // TODO game에서 명령어 검증
        // TODO 게임 결과 출력
        return AppStatus.RUNNING;
    }

    public AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
