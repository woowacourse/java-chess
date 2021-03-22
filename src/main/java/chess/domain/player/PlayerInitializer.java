package chess.domain.player;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInitializer {

    public static Player initPlayer(Owner owner){
        List<Position> positions = new ArrayList<>();

        if(owner.equals(Owner.BLACK)){
            positions.addAll(getHorizontalLine(Horizontal.EIGHT));
            positions.addAll(getHorizontalLine(Horizontal.SEVEN));

            return new Player(positions, owner);
        }

        if(owner.equals(Owner.WHITE)){
            positions.addAll(getHorizontalLine(Horizontal.TWO));
            positions.addAll(getHorizontalLine(Horizontal.ONE));

            return new Player(positions, owner);
        }

        throw new IllegalArgumentException("올바르지 않는 플레이어 생성입니다.");
    }

    private static List<Position> getHorizontalLine(final Horizontal horizontal){
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Position(vertical, horizontal))
                .collect(Collectors.toList());
    }
}
