package chess.pieces;

import chess.piece.ChessPiece;
import chess.position.Position;

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
            final List<ChessPiece> otherChessPieces
    ) {

        final ChessPiece chessPiece = chessPieces.stream()
                .filter(piece -> piece.getPosition().equals(piecePosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("대상 기물이 존재하지 않습니다."));

        boolean isMyChessPieceExistInNewPosition = chessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));

        boolean isOtherChessPieceExistInNewPosition = otherChessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));

        if (isMyChessPieceExistInNewPosition) {
            throw new IllegalArgumentException("우리팀 기물이 존재합니다.");
        }

        if (isOtherChessPieceExistInNewPosition) {
            throw new IllegalArgumentException("상대방 기물이 존재합니다. 공격 명령을 사용하세요.");
        }

        chessPiece.move(newPosition);
    }
}
