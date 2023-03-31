package chess.domain.model.player;

import chess.domain.model.Score;
import chess.domain.model.piece.Piece;
import chess.domain.model.piece.Pieces;
import chess.domain.model.position.Position;
import java.util.List;
import java.util.Optional;

public class Player {

    private final Color color;
    private final Pieces pieces;

    private Player(final Color color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public static Player fromWhitePlayer(final Pieces pieces) {
        return new Player(Color.WHITE, pieces);
    }

    public static Player fromBlackPlayer(final Pieces pieces) {
        return new Player(Color.BLACK, pieces);
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public Color getColor() {
        return color;
    }

    public boolean hasPositionPiece(final Position findPosition) {
        return pieces.hasPosition(findPosition);
    }

    public boolean doesNotHaveKing() {
        return !pieces.containsKing();
    }

    public Piece removePiece(final Position removalPosition) {
        return pieces.remove(removalPosition)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 플레이어의 기물이 없습니다."));
    }

    public Score getTotalScore() {
        return pieces.calculateScore();
    }

    public String getColorName() {
        return color.name();
    }

    public Piece getPieceByPosition(Position position) {
        return pieces.findPiece(position);
    }

    @Override
    public String toString() {
        return "Player{" +
                "color='" + color + '\'' +
                ", pieces=" + pieces +
                '}';
    }

}
