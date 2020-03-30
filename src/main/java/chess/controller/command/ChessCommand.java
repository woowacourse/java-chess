package chess.controller.command;

import chess.controller.ChessContext;
import chess.controller.dto.DefaultRequest;
import chess.controller.dto.MoveRequest;
import chess.manager.ChessManager;
import chess.view.OutputView;

public class ChessCommand {
    public static void start(ChessContext chessContext, DefaultRequest<?> defaultRequest) {
        chessContext.create();
        ChessManager chessManager = chessContext.getChessManager();

        OutputView.showChessBoard(chessManager.getChessBoard());
    }

    public static void move(ChessContext chessContext, DefaultRequest<?> defaultRequest) {
        ChessManager chessManager = chessContext.getChessManager();
        MoveRequest data = (MoveRequest) defaultRequest.getData();

        chessManager.move(data.getFile(), data.getRank());

        OutputView.showChessBoard(chessManager.getChessBoard());
    }

    public static void status(ChessContext chessContext, DefaultRequest<?> defaultRequest) {
        ChessManager chessManager = chessContext.getChessManager();

        OutputView.showScore(chessManager.getCurrentTeam(), chessManager.calculateCurrentTeamScore());
    }

    public static void end(ChessContext chessContext, DefaultRequest<?> defaultRequest) {
        chessContext.end();
    }
}
