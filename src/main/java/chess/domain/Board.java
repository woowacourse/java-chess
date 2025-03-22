package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PromotionOrder;

import java.util.List;

public class Board {

    private final ChessPieces whitePieces;
    private final ChessPieces blackPieces;

    private Color turn = Color.BLACK;

    public Board(final ChessPieces whitePieces, final ChessPieces blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    public void move(final Position piecePosition, final Position newPosition) {
        nextTurn();
        getCurrentTurnPieces().move(piecePosition, newPosition, getEnemyPieces());
    }

    public void take(final Position piecePosition, final Position newPosition) {
        nextTurn();
        getCurrentTurnPieces().take(piecePosition, newPosition, getEnemyPieces());
    }

    public void promotion(final PromotionOrder order) {
        getCurrentTurnPieces().promotion(order);
    }

    public void castling(final Position piecePosition, final Position newPosition) {
        nextTurn();
        getCurrentTurnPieces().castling(piecePosition, newPosition, getEnemyPieces());
    }

    public boolean isCheckMate() {
        nextTurn();
        return getCurrentTurnPieces().isCheckmateBy(getEnemyPieces());
    }

    public boolean isKingDead() {
        return !whitePieces.isKingAlive() || !blackPieces.isKingAlive();
    }

    private void nextTurn() {
        turn = turn.opposite();
    }

    private ChessPieces getCurrentTurnPieces() {
        if (turn == Color.WHITE) {
            return whitePieces;
        }
        if (turn == Color.BLACK) {
            return blackPieces;
        }
        throw new IllegalStateException();
    }

    private ChessPieces getEnemyPieces() {
        if (turn == Color.WHITE) {
            return blackPieces;
        }
        if (turn == Color.BLACK) {
            return whitePieces;
        }
        throw new IllegalStateException();
    }

    public Color getWinnerColor() {
        if (!isKingDead()) {
            throw new IllegalStateException();
        }
        if (whitePieces.isKingAlive()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces.getChessPieces();
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces.getChessPieces();
    }

    public boolean hasPromotionablePawn() {
        return whitePieces.hasPromotionablePawn() || blackPieces.hasPromotionablePawn();
    }
}
