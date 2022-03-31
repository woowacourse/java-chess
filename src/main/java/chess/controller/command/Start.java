package chess.controller.command;

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
    public void execute(final ChessGame chessGame) {
        try {
            final Map<Position, ChessPiece> pieceByPosition = chessGame.start();
            OutputView.printChessBoard(pieceByPosition);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
