package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.movestrategy.PawnMoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private Pawn(Color color) {
        super(color, Symbol.PAWN);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        if (isBlack()) {
            return blackMovable(position);
        }
        return whiteMovable(position);
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new PawnMoveStrategy();
    }

    private List<List<Position>> whiteMovable(Position position) {
        List<List<Position>> positions = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Collections.singletonList(position.up())),
                Arrays.asList(position.leftUp(), position.rightUp())
        ));
        if (position.isSameY(Ypoint.TWO)) {
            positions.get(0).add(position.doubleUp());
        }
        return positions;
    }

    private List<List<Position>> blackMovable(Position position) {
        List<List<Position>> positions = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Collections.singletonList(position.down())),
                Arrays.asList(position.leftDown(), position.rightDown())
        ));
        if (position.isSameY(Ypoint.SEVEN)) {
            positions.get(0).add(position.doubleDown());
        }
        return positions;
    }
}
