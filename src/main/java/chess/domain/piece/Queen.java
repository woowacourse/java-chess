package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.MovementsFactory;
import chess.domain.movement.UnitMovement;
import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {
    private static final int MAX_MOVE_COUNT = 8;
    private static final Set<UnitMovement> COMMON_UNIT_MOVEMENTS =
            Stream.concat(MovementsFactory.createStraight().stream(), MovementsFactory.createDiagonal().stream())
                    .collect(Collectors.toSet());
    private static final Movements COMMON_MOVEMENTS = new Movements(COMMON_UNIT_MOVEMENTS, COMMON_UNIT_MOVEMENTS);
    private static final Map<Color, Queen> QUEEN_POOL = Map.of(
            Color.BLACK, new Queen(Color.BLACK, COMMON_MOVEMENTS),
            Color.WHITE, new Queen(Color.WHITE, COMMON_MOVEMENTS));

    private Queen(Color color, Movements movements) {
        super(color, movements);
    }

    public static Queen from(Color color) {
        return QUEEN_POOL.get(color);
    }

    @Override
    public List<Position> findPassPathTaken(TerminalPosition terminalPosition) {
        return movements.findPassPathTaken(terminalPosition, MAX_MOVE_COUNT);
    }

    @Override
    public List<Position> findAttackPathTaken(TerminalPosition terminalPosition) {
        return movements.findAttackPathTaken(terminalPosition, MAX_MOVE_COUNT);
    }
}
