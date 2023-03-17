package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.state.command.Command;

public class Initialize extends AbstractChessState {

    public Initialize(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public ChessState execute(final Command command) {
        if (command.isStart()) {
            return new Running(chessBoard, new Turn(Color.WHITE));
        }
        throw new IllegalArgumentException("초기화되지 않아 명령을 수행할 수 없습니다.");
    }

    @Override
    public boolean executable() {
        return true;
    }
}
