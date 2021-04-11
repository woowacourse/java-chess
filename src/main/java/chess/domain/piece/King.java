package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.KingMoveStrategy;

import java.util.HashMap;
import java.util.Map;

public class King extends Piece {
    private static final Score SCORE = new Score(0);
    private static final Position INITIAL_BLACK_POSITION = Position.of('e', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('e', '1');

    public King(String name, Color color) {
        super(name, color, SCORE, new KingMoveStrategy());
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }

    public static Map<Position, King> generate() {
        Map<Position, King> kings = new HashMap<>();
        kings.put(INITIAL_BLACK_POSITION, new King("K", Color.BLACK));
        kings.put(INITIAL_WHITE_POSITION, new King("k", Color.WHITE));
        return kings;
    }
}
