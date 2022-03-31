package chess.controller.command;

import chess.controller.result.Result;
import chess.controller.result.StartResult;
import chess.domain.ChessGame;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.view.OutputView;
import java.util.Map;

public class Start implements Command {

    private static final Start INSTANCE = new Start();

    private Start() {
    }

    public static Start getInstance() {
        return INSTANCE;
    }

    @Override
    public Result execute(final ChessGame chessGame) {
        final Map<Position, ChessPiece> pieceByPosition = chessGame.start();
        return new StartResult(pieceByPosition);
    }
}
