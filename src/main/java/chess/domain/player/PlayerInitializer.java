package chess.domain.player;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInitializer {

    private PlayerInitializer() {
    }

    public static Player initPlayer(final Owner owner) {
        final List<Position> positions = new ArrayList<>();

        if (owner.isBlack()) {
            positions.addAll(getHorizontalLine(Horizontal.EIGHT));
            positions.addAll(getHorizontalLine(Horizontal.SEVEN));
            return new Player(positions, owner);
        }

        if (owner.isWhite()) {
            positions.addAll(getHorizontalLine(Horizontal.TWO));
            positions.addAll(getHorizontalLine(Horizontal.ONE));
            return new Player(positions, owner);
        }

        throw new IllegalArgumentException("올바르지 않는 플레이어 생성입니다.");
    }

    private static List<Position> getHorizontalLine(final Horizontal horizontal) {
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Position(vertical, horizontal))
                .collect(Collectors.toList());
    }
}
