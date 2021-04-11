package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;

public final class ChessGame {
    private final Team blackTeam;
    private final Team whiteTeam;
    private Team currentTurnTeam;
    private boolean isPlaying;

    public ChessGame(final Team blackTeam, final Team whiteTeam, final Team currentTurnTeam, final boolean isPlaying) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurnTeam = currentTurnTeam;
        this.isPlaying = isPlaying;
    }

    public ChessGame(final Team blackTeam, final Team whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurnTeam = this.whiteTeam;
        this.isPlaying = true;
    }

    public void move(final Position current, final Position destination) {
        if (checkValidMove(current, destination)) {
            makeSpecialMove(current, destination);
            currentTurnTeam.movePiece(current, destination);
            captureEnemy(destination);
            changeTurn();
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    private boolean checkValidMove(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurnTeam.choosePiece(current);
        return chosenPiece.isMovable(current, destination, generateChessBoard());
    }

    private void makeSpecialMove(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurnTeam.choosePiece(current);
        if (chosenPiece.isKing() && chosenPiece.isFirstMove()) {
            checkKingSideCastling(current, destination);
            checkQueenSideCastling(current, destination);
        }
        if (chosenPiece.isPawn() && destination.isEndRank()) {
            promote(current);
        }
    }

    private void checkKingSideCastling(final Position kingCurrent, final Position kingDestination) {
        if (kingCurrent.moveXandY(2, 0).equals(kingDestination)) {
            final Position kingSideRook = kingDestination.moveXandY(1, 0);
            makeCastling(kingSideRook, kingDestination);
        }
    }

    private void checkQueenSideCastling(final Position kingCurrent, final Position kingDestination) {
        if (kingCurrent.moveXandY(-2, 0).equals(kingDestination)) {
            final Position queenSideRook = kingDestination.moveXandY(-2, 0);
            makeCastling(queenSideRook, kingDestination);
        }
    }

    private void makeCastling(final Position castlingRook, final Position kingDestination) {
        final Piece piece = currentTurnTeam.choosePiece(castlingRook);
        if (piece.isRook() && piece.isFirstMove()) {
            currentTurnTeam.moveCastlingRook(kingDestination);
        }
    }

    private void promote(final Position current) {
        currentTurnTeam.promotePiece(current);
    }

    public Map<Position, Piece> generateChessBoard() {
        final Map<Position, Piece> chessBoard = blackTeam.currentPiecePosition();
        chessBoard.putAll(whiteTeam.currentPiecePosition());
        return new HashMap<>(chessBoard);
    }

    private void captureEnemy(final Position destination) {
        final Team enemyTeam = getEnemy();
        if (enemyTeam.havePiece(destination)) {
            final Piece enemyPiece = enemyTeam.deletePiece(destination);
            currentTurnTeam.catchPiece(enemyPiece);
            finishIfKingIsCaught(enemyPiece);
        }
    }

    private Team getEnemy() {
        if (currentTurnTeam == blackTeam) {
            return whiteTeam;
        }
        return blackTeam;
    }

    private void finishIfKingIsCaught(final Piece piece) {
        if (piece.isKing()) {
            finish();
        }
    }

    private void changeTurn() {
        currentTurnTeam = getEnemy();
    }

    public void finish() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public double calculateBlackTeamScore() {
        return blackTeam.calculateScore();
    }

    public double calculateWhiteTeamScore() {
        return whiteTeam.calculateScore();
    }

    public Map<Position, Piece> currentBlackPiecePosition() {
        return blackTeam.currentPiecePosition();
    }

    public Map<Position, Piece> currentWhitePiecePosition() {
        return whiteTeam.currentPiecePosition();
    }

    public boolean isWhiteTeamTurn() {
        return currentTurnTeam == whiteTeam;
    }
}
