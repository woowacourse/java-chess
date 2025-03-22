package chess.piece;

import java.util.Optional;

public class RemovePiece {

    private final Pawn movedPiece;
    private final Pawn enemy;

    public RemovePiece(Pawn movedPiece, Pawn enemy) {
        this.movedPiece = movedPiece;
        this.enemy = enemy;
    }

    public Optional<Pawn> getDeadPiece() {
        if(movedPiece.getPosition().equals(enemy.getPosition()) && movedPiece.isOpposite(enemy)){
            return Optional.of(enemy);
        }
        return Optional.empty();
    }
}
