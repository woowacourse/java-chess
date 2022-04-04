package chess.db.entity;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.Objects;

public class FullGameEntity {

    private final GameEntity game;
    private final List<PieceEntity> pieces;

    public FullGameEntity(GameEntity game, List<PieceEntity> pieces) {
        this.game = game;
        this.pieces = pieces;
    }

    public GameEntity getGame() {
        return game;
    }

    public List<PieceEntity> getPieces() {
        return pieces;
    }

    public PieceEntity getPieceAt(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition() == position)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 존재하는 체스말은 없습니다."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullGameEntity that = (FullGameEntity) o;
        return Objects.equals(game, that.game) && Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, pieces);
    }

    @Override
    public String toString() {
        return "FullGameEntity{" + "game=" + game + ", pieces=" + pieces + '}';
    }
}
