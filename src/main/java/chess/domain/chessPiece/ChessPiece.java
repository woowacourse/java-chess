package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.chessPiece.pieceType.PieceTypeCache;
import chess.domain.position.Position;

import java.util.Objects;

// NOTE: 2020/03/26 여기에 state를 준다면? 새롬게 PieceTypeCache가 캐싱을 하고 있고, 여기선 PieceType을 가져와 state를 조합!
public class ChessPiece implements Movable, Catchable {
    private final PieceType pieceType;

    private ChessPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    // TODO: 2020/03/26 추후 state가 추가될 경우, key와 state를 받아서 생성하는 from으로 변경
    public static ChessPiece of(String key) {
        Objects.requireNonNull(key, "체스 피스의 key가 null입니다.");
        return new ChessPiece(PieceTypeCache.from(key));
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return pieceType.canMove(source, target);
    }

    @Override
    public boolean canLeap() {
        return pieceType.canLeap();
    }

    @Override
    public boolean canCatch(Position source, Position target) {
        return pieceType.canCatch(source, target);
    }

    @Override
    public String toString() {
        return this.pieceType.getName();
    }

    public boolean isSamePieceColor(ChessPiece targetPiece) {
        return this.pieceType.isSamePieceColor(targetPiece.pieceType);
    }
}
