package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.PawnMoveStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final Score SCORE = new Score(1);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '7'),
            Position.of('b', '7'), Position.of('c', '7'), Position.of('d', '7'),
            Position.of('e', '7'), Position.of('f', '7'), Position.of('g', '7'),
            Position.of('h', '7'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '2'),
            Position.of('b', '2'), Position.of('c', '2'), Position.of('d', '2'),
            Position.of('e', '2'), Position.of('f', '2'), Position.of('g', '2'),
            Position.of('h', '2'));

    public Pawn(String name, Color color) {
        super(name, color, SCORE, new PawnMoveStrategy(color));
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }

    public static Map<Position, Pawn> generate() {
        Map<Position, Pawn> blackPawns = INITIAL_BLACK_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Pawn("P", Color.BLACK)));
        Map<Position, Pawn> whitePawns = INITIAL_WHITE_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Pawn("p", Color.WHITE)));
        blackPawns.putAll(whitePawns);
        return blackPawns;
    }

    public boolean isAttackAble(Position source, Position target) {
        PawnMoveStrategy pawnMoveStrategy = (PawnMoveStrategy) moveStrategy;
        return pawnMoveStrategy.isAttackAble(source, target);
    }
}
