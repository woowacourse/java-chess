package chess.domain.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.GameCommand;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public final class WhiteRunning extends Running {

    @Override
    public State move(final ChessBoard chessBoard, final GameCommand gameCommand) {
        if (isWhiteTurn(chessBoard, gameCommand)) {
            chessBoard.move(gameCommand.getFromPosition(), gameCommand.getToPosition());
            return new BlackRunning();
        }
        if (isEmpty(chessBoard, gameCommand)) {
            throw new IllegalStateException("비어있습니다.");
        }
        throw new IllegalStateException("검은색 차례가 아닙니다.");
    }

    private boolean isWhiteTurn(final ChessBoard chessBoard, final GameCommand gameCommand) {
        final Position fromPosition = gameCommand.getFromPosition();
        final Piece piece = chessBoard.selectPiece(fromPosition);
        return piece.isWhite();
    }

    @Override
    public String toString() {
        return "WhiteRunning";
    }
}
