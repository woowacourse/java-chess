package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.CommonMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private Queen(Color color) {
        super(color, Symbol.QUEEN);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>(Arrays.asList(
                position.upVector(),
                position.downVector(),
                position.leftVector(),
                position.rightVector(),
                position.leftUpVector(),
                position.leftDownVector(),
                position.rightUpVector(),
                position.rightDownVector()
        ));
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new CommonMoveStrategy();
    }
}
