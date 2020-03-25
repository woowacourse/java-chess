package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    private final String name;
    protected List<MoveRule> moveRules = new ArrayList<>();

    public ChessPiece(String name) {
        this.name = name;
    }

    public abstract void move(Position source, Position target);

    public String getName() {
        return name;
    }

    public List<MoveRule> getMoveRules() {
        return moveRules;
    }
}
