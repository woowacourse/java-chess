package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public final class WhiteTurn extends Start {

    @Override
    public State move(ChessBoard chessBoard, GameCommand gameCommand) {
        Position fromPosition = gameCommand.getFromPosition();
        Piece piece = chessBoard.selectPiece(fromPosition);
        Color color = piece.getColor();
        if (color == Color.WHITE) {
            return new BlackTurn();
        }
        throw new IllegalStateException("검은색 차례가 아닙니다.");
    }

    @Override
    public Color getWinner() {
        return Color.BLACK;
    }
}
