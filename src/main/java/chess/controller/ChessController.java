package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.domain.Camp;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.view.OutputView;

public class ChessController {

    private ChessGame chessGame;

    public AppStatus start(CommandRequest commandRequest) {
        chessGame = new ChessGame(Camp.WHITE, Camp::transfer);
        chessGame.start(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard(commandRequest)));
        return AppStatus.RUNNING;
    }

    public AppStatus move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard(commandRequest)));
        return AppStatus.RUNNING;
    }

    public AppStatus end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    public AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
