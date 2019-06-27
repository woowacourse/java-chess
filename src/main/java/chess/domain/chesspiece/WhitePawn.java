package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.List;

public class WhitePawn implements PawnChessPiece {
    public static final double SCORE = 1.0;
    private static final List<ChessDirection> UNIT_DIRECTION_EMPTY_ON_TARGET
            = ChessDirection.whitePawnDirectionOnEmptyTarget();
    private static final List<ChessDirection> UNIT_DIRECTIONS_OPPONENT_ON_TARGET
            = ChessDirection.whitePawnDirectionOnExistTarget();
    private static final String NAME = "P";
    private static WhitePawn whitePawn = null;

    private WhitePawn() {
    }

    public static WhitePawn getInstance() {
        if (whitePawn == null) {
            whitePawn = new WhitePawn();
        }
        return whitePawn;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint direction = target.minus(source);

        return opponentPieceOnTarget
                ? UNIT_DIRECTIONS_OPPONENT_ON_TARGET.stream().anyMatch(d -> d.match(direction))
                : UNIT_DIRECTION_EMPTY_ON_TARGET.stream().anyMatch(d -> d.match(direction));
    }

    @Override
    public Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column) {
        pawnCounter.increase(column);
        return pawnCounter;
    }

    @Override
    public double getScore(Counter<Integer> pawnCounter, int column) {
        return (1 < pawnCounter.count(column)) ? SCORE / 2 : SCORE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean hasName(String name) {
        return NAME.equals(name);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
