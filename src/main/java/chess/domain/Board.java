package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.pieces.ChessPieces;
import chess.domain.pieces.Color;
import chess.domain.position.Position;

import java.util.List;

public class Board {

    private final ChessPieces whitePieces;
    private final ChessPieces blackPieces;

    private Color turn = Color.WHITE;

    public Board(final ChessPieces pieces1, final ChessPieces pieces2) {
        // TODO : 하나는 검정색, 하나는 흰색 이어야 함
        // 색깔에 맞춰 넣기
        this.whitePieces = pieces1;
        this.blackPieces = pieces2;
    }

    public void move(
            final Position piecePosition,
            final Position newPosition
    ) {
        getCurrentTurnPieces().move(piecePosition, newPosition, getEnemyPieces());
        nextTurn();
    }

    public void take(
            final Position piecePosition,
            final Position newPosition
    ) {
        getCurrentTurnPieces().take(piecePosition, newPosition, getEnemyPieces());
        nextTurn();
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

    public boolean isGameEnd() {
        return !whitePieces.isKingAlive() || blackPieces.isKingAlive();
    }

    public Color getWinnerColor() {
        if (!isGameEnd()) {
            throw new IllegalStateException();
        }
        if (whitePieces.isKingAlive()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public List<ChessPiece> getPieces() {
        return null;
    }
}
