package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.CommonMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private King(Color color) {
        super(color, Symbol.KING);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>(Arrays.asList(
                Collections.singletonList(position.up()),
                Collections.singletonList(position.down()),
                Collections.singletonList(position.left()),
                Collections.singletonList(position.right()),
                Collections.singletonList(position.leftUp()),
                Collections.singletonList(position.leftDown()),
                Collections.singletonList(position.rightUp()),
                Collections.singletonList(position.rightDown())));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new CommonMoveStrategy();
    }
}
