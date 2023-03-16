package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;

public class Initialize extends AbstractChessState {

    public Initialize(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public ChessState command(final Command command) {
        if (!command.getCommand().equals("start")) {
            throw new IllegalArgumentException();
        }
        return new Running(chessBoard, new Turn(Color.WHITE));
    }

    @Override
    public boolean runnable() {
        return true;
    }
}
