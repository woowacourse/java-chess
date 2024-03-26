package chess.domain.movement;

import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Movements {
    private final Set<UnitMovement> passMovements;
    private final Set<UnitMovement> attackMovements;

    public Movements(Set<UnitMovement> passMovements, Set<UnitMovement> attackMovements) {
        this.passMovements = new HashSet<>(passMovements);
        this.attackMovements = new HashSet<>(attackMovements);
    }

    public List<Position> findPassPathTaken(TerminalPosition terminalPosition, int maxMoveCount) {
        UnitMovement arriveMovement = passMovements.stream()
                .filter(unitMovement -> canArrive(terminalPosition, unitMovement, maxMoveCount))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("도착 위치는 체스 말이 도달할 수 없는 위치입니다."));

        return findPathTaken(terminalPosition, arriveMovement);
    }

    public List<Position> findAttackPathTaken(TerminalPosition terminalPosition, int maxMoveCount) {
        UnitMovement arriveMovement = attackMovements.stream()
                .filter(unitMovement -> canArrive(terminalPosition, unitMovement, maxMoveCount))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("도착 위치는 체스 말이 도달할 수 없는 위치입니다."));

        return findPathTaken(terminalPosition, arriveMovement);
    }

    private boolean canArrive(TerminalPosition terminalPosition, UnitMovement unitMovement, int maxMoveCount) {
        Position startPosition = terminalPosition.getStart();
        Position endPosition = terminalPosition.getEnd();

        return Stream.iterate(startPosition, position -> position.move(unitMovement))
                .limit(maxMoveCount)
                .takeWhile(currentPosition -> currentPosition.canMove(unitMovement))
                .anyMatch(currentPosition -> endPosition.equals(nextPosition(currentPosition, unitMovement)));
    }

    private Position nextPosition(Position position, UnitMovement unitMovement) {
        return position.move(unitMovement);
    }

    private List<Position> findPathTaken(TerminalPosition terminalPosition, UnitMovement unitMovement) {
        ArrayList<Position> pathTaken = new ArrayList<>();
        Position current = terminalPosition.getStart();
        current = current.move(unitMovement);

        while (!current.equals(terminalPosition.getEnd())) {
            pathTaken.add(current);
            current = current.move(unitMovement);
        }

        return pathTaken;
    }

    @Override
    public String toString() {
        return "Movements{" +
                "passMovements=" + passMovements +
                ", attackMovements=" + attackMovements +
                '}';
    }
}
