package chess.domain.pieces;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.dto.PromotionOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessPieces {

    private final List<ChessPiece> chessPieces;

    public ChessPieces(final List<ChessPiece> chessPieces) {
        this.chessPieces = new ArrayList<>(chessPieces);
    }

    public void move(
            final Position piecePosition,
            final Position newPosition,
            final ChessPieces otherChessPieces
    ) {

        final ChessPiece chessPiece = getPieceOf(piecePosition);
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
        final ChessPiece chessPiece = getPieceOf(piecePosition);

        if (isIsMyChessPieceExistInNewPosition(newPosition)) {
            throw new IllegalArgumentException("우리팀 기물이 존재합니다.");
        }
        if (!isOtherChessPieceExistInNewPosition(newPosition, otherChessPieces)) {
            throw new IllegalArgumentException("상대방 기물이 존재하지 않습니다. 이동 명령을 사용하세요.");
        }

        if (!chessPiece.canTake(newPosition)) {
            throw new IllegalArgumentException("잡을 수 없는 위치입니다.");
        }
        otherChessPieces.remove(newPosition);
        chessPiece.take(newPosition);
    }

    private void remove(final Position position) {
        chessPieces.remove(getPieceOf(position));
    }

    private static boolean isOtherChessPieceExistInNewPosition(final Position newPosition, final ChessPieces otherChessPieces) {
        return otherChessPieces.chessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));
    }

    private boolean isIsMyChessPieceExistInNewPosition(final Position newPosition) {
        return chessPieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(newPosition));
    }

    private ChessPiece getPieceOf(final Position piecePosition) {
        return chessPieces.stream()
                .filter(piece -> piece.getPosition().equals(piecePosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("대상 기물이 존재하지 않습니다."));
    }

    public boolean isKingAlive() {
        return chessPieces.stream()
                .anyMatch(piece -> piece instanceof King);
    }

    public List<ChessPiece> getChessPieces() {
        return chessPieces;
    }

    public boolean hasPromotionablePawn() {
        return getPromotionablePawn().isPresent();
    }

    public void promotion(final PromotionOrder order) {
        final Optional<ChessPiece> promotionablePawn = getPromotionablePawn();
        if (promotionablePawn.isEmpty()) {
            throw new IllegalArgumentException("프로모션 가능한 폰이 없습니다.");
        }
        final ChessPiece targetPawn = promotionablePawn.get();
        switch (order) {
            case PromotionOrder.QUEEN -> chessPieces.add(new Queen(targetPawn.getColor(), targetPawn.getPosition()));
            case PromotionOrder.ROOK -> chessPieces.add(new Rook(targetPawn.getColor(), targetPawn.getPosition()));
            case PromotionOrder.BISHOP -> chessPieces.add(new Bishop(targetPawn.getColor(), targetPawn.getPosition()));
            case PromotionOrder.KNIGHT -> chessPieces.add(new Knight(targetPawn.getColor(), targetPawn.getPosition()));
        }

        chessPieces.remove(targetPawn);
    }

    private Optional<ChessPiece> getPromotionablePawn() {
        return chessPieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .filter(ChessPiece::isPromotionable)
                .findFirst();
    }

    public void castling(final Position piecePosition, final Position newPosition) {
        final ChessPiece piece1 = getPieceOf(piecePosition);
        final ChessPiece piece2 = getPieceOf(newPosition);
        piece1.castling(piece2);
    }
}
