package chess.domain.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.CommandType;
import chess.domain.command.GameCommand;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class Running implements State {

    public abstract State move(final ChessBoard chessBoard, final GameCommand gameCommand);

    @Override
    public final State proceed(final ChessBoard chessBoard, final GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            return start();
        }
        if (gameCommand.isSameCommandType(CommandType.END)) {
            return end();
        }
        if (gameCommand.isSameCommandType(CommandType.MOVE)) {
            return move(chessBoard, gameCommand);
        }
        return this;
    }

    private State start() {
        throw new IllegalStateException("시작 상태에서는 다시 시작할 수 없습니다.");
    }

    private State end() {
        return new Finish();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    protected boolean isEmpty(final ChessBoard chessBoard, final GameCommand gameCommand) {
        final Position fromPosition = gameCommand.getFromPosition();
        final Piece piece = chessBoard.selectPiece(fromPosition);
        return piece.isEmpty();
    }
}
