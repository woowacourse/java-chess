package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '7'),
            Position.of('b', '7'), Position.of('c', '7'), Position.of('d', '7'),
            Position.of('e', '7'), Position.of('f', '7'), Position.of('g', '7'),
            Position.of('h', '7'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '2'),
            Position.of('b', '2'), Position.of('c', '2'), Position.of('d', '2'),
            Position.of('e', '2'), Position.of('f', '2'), Position.of('g', '2'),
            Position.of('h', '2'));

    public Pawn(Position position, String name) {
        super(position, name);
    }

    @Override
    void move(Position position, CurrentPieces currentPieces) {
        if (this.position.getY() == '7' && this.name.equals("P")) { // 블랙
            if (this.position.subtractY(position) > 0 && this.position.subtractY(position) <= 2) {
                this.position = position;
                return;
            }
        }

        if (this.position.getY() == '2' && this.name.equals("p")) { // 화이트
            if (position.subtractY(this.position) > 0 && position.subtractY(this.position) <= 2) {
                this.position = position;
                return;
            }
        }

        if (this.position.subtractY(position) == 1 && this.name.equals("P")) { // 블랙
            this.position = position;
            return;
        }

        if (position.subtractY(this.position) == 1 && this.name.equals("p")) { // 화이트
            this.position = position;
            return;
        }
        throw new IllegalArgumentException("[ERROR] 움직일 수 없는 위치입니다.");
    }

    public static List<Pawn> generate() {
        List<Pawn> blackPawns = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Pawn(position, "P"))
                .collect(Collectors.toList());
        List<Pawn> whitePawns = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Pawn(position, "p"))
                .collect(Collectors.toList());
        blackPawns.addAll(whitePawns);
        return blackPawns;
    }
}
