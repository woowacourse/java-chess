package chess;

import chess.domain.piece.Color;
import chess.domain.piece.NonPawnPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.KingMove;
import chess.domain.strategy.piecemovestrategy.PawnMoveStrategy;

public class PieceFactory {

    public static Piece from(final String piece) {
        final String[] split = piece.split("~");
        final Color color = Color.valueOf(split[0]);
        final Position position = Position.of(split[2]);
        switch (split[1]) {
            case "pawn": {
                return new Pawn(color, position, PawnMoveStrategy.from(color));
            }
            case "king": {
                return new NonPawnPiece(color, position, KingMove.getInstance());
            }
        }
        throw new IllegalArgumentException("PieceFactory오류");
    }
}
