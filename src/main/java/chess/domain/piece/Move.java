package chess.domain.piece;

import chess.domain.piece.condition.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Move {
    P("P", Arrays.asList(new FirstTurnBlackPawnMoveCondition(), new NormalBlackPawnMoveCondition(), new CatchingPieceBlackPawnMoveCondition())),
    K("K", Collections.singletonList(new KingMoveCondition())),
    N("N", Collections.singletonList(new KnightMoveCondition())),
    B("B", Collections.singletonList(new BishopMoveCondition())),
    Q("Q", Collections.singletonList(new QueenMoveCondition())),
    R("R", Collections.singletonList(new RookMoveCondition())),
    p("p", Arrays.asList(new FirstTurnWhitePawnMoveCondition(), new NormalWhitePawnMoveCondition(), new CatchingPieceWhitePawnMoveCondition())),
    k("k", Collections.singletonList(new KingMoveCondition())),
    n("n", Collections.singletonList(new KnightMoveCondition())),
    b("b", Collections.singletonList(new BishopMoveCondition())),
    q("q", Collections.singletonList(new QueenMoveCondition())),
    r("r", Collections.singletonList(new RookMoveCondition()));

    private String notation;
    private final List<MoveCondition> moveConditions;

    Move(String notation, List<MoveCondition> moveConditions) {
        this.notation = notation;
        this.moveConditions = moveConditions;
    }

    public List<MoveCondition> getMoveCondition() {
        return moveConditions;
    }
}
