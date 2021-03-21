package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.KnightMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private Knight(Color color) {
        super(color, Symbol.KNIGHT);
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>(Arrays.asList(
                Collections.singletonList(position.upUpLeft()),
                Collections.singletonList(position.upUpRight()),
                Collections.singletonList(position.downDownLeft()),
                Collections.singletonList(position.downDownRight()),
                Collections.singletonList(position.leftLeftUp()),
                Collections.singletonList(position.leftLeftDown()),
                Collections.singletonList(position.rightRightUp()),
                Collections.singletonList(position.rightRightDown())));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new KnightMoveStrategy();
    }
}
