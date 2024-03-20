package chess.chesspiece;

import chess.chesspiece.movestrategy.MoveStrategy;

public class ChessPieceProperty {

    private final ChessPieceType chessPieceType;
    private final MoveStrategy moveStrategy;

    public ChessPieceProperty(ChessPieceType chessPieceType, MoveStrategy moveStrategy) {
        this.chessPieceType = chessPieceType;
        this.moveStrategy = moveStrategy;
    }
}
