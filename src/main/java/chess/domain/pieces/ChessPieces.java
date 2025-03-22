package chess.domain.pieces;

import chess.domain.piece.ChessPiece;
import chess.domain.position.Position;

import java.util.List;

public class ChessPieces {

    private final Color color;
    private final List<ChessPiece> chessPieces;

    public ChessPieces(final Color color, final List<ChessPiece> chessPieces) {
        this.color = color;
        this.chessPieces = chessPieces;
    }

    public void move(
            final Position piecePosition,
            final Position newPosition,
            final ChessPieces otherChessPieces
    ) {

        final ChessPiece chessPiece = getTargetPiece(piecePosition);
        if (isIsMyChessPieceExistInNewPosition(newPosition)) {
            throw new IllegalArgumentException("우리팀 기물이 존재합니다.");
        }
        if (isOtherChessPieceExistInNewPosition(newPosition, otherChessPieces)) {
            throw new IllegalArgumentException("상대방 기물이 존재합니다. 공격 명령을 사용하세요.");
        }
        chessPiece.move(newPosition);
    }

    public void take(
            final Position piecePosition,
            final Position newPosition,
            final ChessPieces otherChessPieces
    ) {
        final ChessPiece chessPiece = getTargetPiece(piecePosition);

        if (isIsMyChessPieceExistInNewPosition(newPosition)) {
            throw new IllegalArgumentException("우리팀 기물이 존재합니다.");
        }
        if (isOtherChessPieceExistInNewPosition(newPosition, otherChessPieces)) {
            throw new IllegalArgumentException("상대방 기물이 존재하지 않습니다. 이동 명령을 사용하세요.");
        }

        chessPieces.remove(getTargetPiece(newPosition));
        chessPiece.move(newPosition);
    }

    private static boolean isOtherChessPieceExistInNewPosition(final Position newPosition, final ChessPieces otherChessPieces) {
        return otherChessPieces.chessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));
    }

    private boolean isIsMyChessPieceExistInNewPosition(final Position newPosition) {
        return chessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));
    }

    private ChessPiece getTargetPiece(final Position piecePosition) {
        return chessPieces.stream()
                .filter(piece -> piece.getPosition().equals(piecePosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("대상 기물이 존재하지 않습니다."));
    }
}
