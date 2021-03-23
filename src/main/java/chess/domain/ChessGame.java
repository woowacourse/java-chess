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

    public ChessGame() {
        this(Team.blackTeam(), Team.whiteTeam());
    }

    public ChessGame(final Team blackTeam, final Team whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurnTeam = this.whiteTeam;
        this.isPlaying = true;
    }

    public void move(final Position current, final Position destination) {
        if (checkValidMove(current, destination)) {
            currentTurnTeam.movePiece(current, destination);
            captureEnemy(destination);
            changeTurn();
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    private boolean checkValidMove(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurnTeam.choosePiece(current);
        if (checkSpecialMove(chosenPiece, current, destination)) {
            return true;
        }
        return chosenPiece.isMovable(current, destination, generateChessBoard());
    }

    private boolean checkSpecialMove(final Piece chosenPiece, final Position current, final Position destination) {
        if (checkCastlingMove(chosenPiece, current, destination)) {
            return true;
        }
        return checkPromotionMove(chosenPiece, current, destination);
    }

    private boolean checkCastlingMove(final Piece chosenPiece, final Position current, final Position destination) {
        if (chosenPiece.isKing() && chosenPiece.isCastlingMovable(current, destination, generateChessBoard())) {
            currentTurnTeam.moveCastlingRook(destination);
            return true;
        }
        return false;
    }

    private boolean checkPromotionMove(final Piece chosenPiece, final Position current, final Position destination) {
        if (chosenPiece.isPawn() && chosenPiece.isPromotionMovable(current, destination, generateChessBoard())) {
            currentTurnTeam.promotePiece(current);
            return true;
        }
        return false;
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
            checkMate(enemyPiece);
        }
    }

    private Team getEnemy() {
        if (currentTurnTeam == blackTeam) {
            return whiteTeam;
        }
        return blackTeam;
    }

    private void checkMate(final Piece piece) {
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
}
