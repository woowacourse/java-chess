package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Board {
    private final List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board initialize() {
        List<Piece> pieces = new ArrayList<>();

        for (Color color : Color.validColors()) {
            pieces.addAll(Bishop.initialize(color));
            pieces.addAll(King.initialize(color));
            pieces.addAll(Knight.initialize(color));
            pieces.addAll(Pawn.initialize(color));
            pieces.addAll(Queen.initialize(color));
            pieces.addAll(Rook.initialize(color));
        }
        return new Board(pieces);
    }

    //TODO 존재하는 포지션만 주기
    public Hurdles findHurdlePositions() {
        return new Hurdles(pieces.stream()
                .map(Piece::getPosition)
                .toList());
    }

    public Optional<Piece> findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position)) //TODO 수정
                .findFirst();
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public Piece findByPositionOrThrow(Position position) {
        return findByPosition(position)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 좌표에 기물이 없습니다."));
    }

    public void move(Piece movingPiece, Position targetPosition) {
        movingPiece.moveTo(targetPosition, this);
    }

    public void remove(Position targetPosition) {
        Piece piece = findByPositionOrThrow(targetPosition);
        this.pieces.remove(piece);
    }
}
