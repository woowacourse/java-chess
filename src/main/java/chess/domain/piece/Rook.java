package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.CommonMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private Rook(Color color) {
        super(color, Symbol.ROOK);
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>(Arrays.asList(
                position.upVector(),
                position.downVector(),
                position.leftVector(),
                position.rightVector()
        ));
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
