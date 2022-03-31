package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public final class BlackTurn extends Start {

    @Override
    public State move(ChessBoard chessBoard, GameCommand gameCommand) {
        if (isBlackTurn(chessBoard, gameCommand)) {
            chessBoard.move(gameCommand);
            return new WhiteTurn();
        }
        if (isEmpty(chessBoard, gameCommand)) {
            throw new IllegalStateException("비어있습니다.");
        }
        throw new IllegalStateException("흰색 차례가 아닙니다.");
    }

    private boolean isEmpty(ChessBoard chessBoard, GameCommand gameCommand) {
        Position fromPosition = gameCommand.getFromPosition();
        Piece piece = chessBoard.selectPiece(fromPosition);
        return piece.isEmpty();
    }

    private boolean isBlackTurn(ChessBoard chessBoard, GameCommand gameCommand) {
        Position fromPosition = gameCommand.getFromPosition();
        Piece piece = chessBoard.selectPiece(fromPosition);
        return piece.isBlack();
    }

    @Override
    public Color getWinner() {
        return Color.WHITE;
    }
}
