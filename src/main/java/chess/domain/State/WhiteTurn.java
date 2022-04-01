package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public final class WhiteTurn extends Start {

    @Override
    public State move(final ChessBoard chessBoard, final GameCommand gameCommand) {
        if (isWhiteTurn(chessBoard, gameCommand)) {
            chessBoard.move(gameCommand);
            return new BlackTurn();
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
    public Color getWinner() {
        return Color.BLACK;
    }
}
