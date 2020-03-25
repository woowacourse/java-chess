package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece extends Square{
    private final Player player;

    protected List<MoveRule> moveRules = new ArrayList<>();


    public Piece(Player player, PieceName name) {
        super(name.getName(player));
        this.player = player;
    }


    public boolean movable(Position source, Position target){
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        int rowDiff = Row.getDiff(source.getRow(), target.getRow());
        int columnDiff = Column.getDiff(source.getColumn(), target.getColumn());
        return validateMovableDirection(rowDiff, columnDiff)
                && validateMovableTileSize(rowDiff, columnDiff);
    }

    public abstract boolean validateMovableTileSize(int rowDiff, int columnDiff);

    public boolean validateMovableDirection(int rowDiff, int columnDiff) {
        return moveRules.stream()
                .anyMatch(moveRule -> moveRule.getJudge().test(rowDiff, columnDiff));
    }

    public List<MoveRule> getMoveRules() {
        return moveRules;
    }
}
