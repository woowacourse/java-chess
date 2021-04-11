package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.RookMoveStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final Score SCORE = new Score(5);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '8'),
            Position.of('h', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '1'),
            Position.of('h', '1'));

    public Rook(String name, Color color) {
        super(name, color, SCORE, new RookMoveStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }

    public static Map<Position, Rook> generate() {
        Map<Position, Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Rook("R", Color.BLACK)));
        Map<Position, Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Rook("r", Color.WHITE)));
        blackRooks.putAll(whiteRooks);
        return blackRooks;
    }
}
