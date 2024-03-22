package chess.domain.chesspiece;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.movestrategy.MoveStrategy;

public class ChessPieceProperty {

    private final ChessPieceType chessPieceType;
    private final MoveStrategy moveStrategy;

    public ChessPieceProperty(ChessPieceType chessPieceType, MoveStrategy moveStrategy) {
        this.chessPieceType = chessPieceType;
        this.moveStrategy = moveStrategy;
    }

    public void executeMoveStrategy(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        moveStrategy.move(chessBoard, startSquare, targetSquare);
    }

    public ChessPieceType getChessPieceType() {
        return chessPieceType;
    }
}
