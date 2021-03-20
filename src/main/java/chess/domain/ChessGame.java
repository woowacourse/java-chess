package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;

import java.util.Collections;
import java.util.Map;

public class ChessGame {
    private final BlackTeam blackTeam;
    private final WhiteTeam whiteTeam;
    private Team currentTurnTeam;
    private boolean isPlaying;

    public ChessGame(final BlackTeam blackTeam, final WhiteTeam whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurnTeam = this.whiteTeam;
        this.isPlaying = true;
    }

    public void move(final Position current, final Position destination) {
        validateMove(current, destination);
        currentTurnTeam.move(current, destination);
        captureEnemy(destination);
    }

    private void validateMove(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurnTeam.choosePiece(current);
        if (chosenPiece.isMovable(current, destination, generateChessBoard())) {
            chosenPiece.isMoved();
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    public Map<Position, Piece> generateChessBoard() {
        final Map<Position, Piece> chessBoard = blackTeam.getPiecePosition();
        chessBoard.putAll(whiteTeam.getPiecePosition());
        return Collections.unmodifiableMap(chessBoard);
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

    public void finish() {
        isPlaying = false;
    }

    public void changeTurn() {
        currentTurnTeam = getEnemy();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public double calculateBlackTeamScore() {
        return blackTeam.calculateTotalScore();
    }

    public double calculateWhiteTeamScore() {
        return whiteTeam.calculateTotalScore();
    }

    public BlackTeam getBlackTeam() {
        return blackTeam;
    }

    public WhiteTeam getWhiteTeam() {
        return whiteTeam;
    }
}
