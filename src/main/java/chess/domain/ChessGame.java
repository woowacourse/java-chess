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
    private Team currentTurn;
    private boolean isPlaying;

    public ChessGame(final BlackTeam blackTeam, final WhiteTeam whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurn = this.whiteTeam;
        this.isPlaying = true;
    }

    public void move(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurn.choosePiece(current);
        validateMovable(current, destination, chosenPiece);

        Team enemy = getEnemy();
        if (enemy.havePiece(destination)) {
            killEnemyPiece(destination, enemy);
        }

        currentTurn.move(current, destination);
        chosenPiece.isMoved();
    }

    private void validateMovable(final Position current, final Position destination, final Piece chosenPiece) {
        if (currentTurn.havePiece(destination) || !chosenPiece.isMovable(current, destination, generateChessBoard())) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void killEnemyPiece(Position destination, Team enemy) {
        Piece piece = enemy.killPiece(destination);
        currentTurn.catchPiece(piece);
        checkMate(piece);
    }

    private void checkMate(Piece piece) {
        if (piece.isKing()) {
            finish();
        }
    }

    public void finish() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    private Team getEnemy() {
        if (currentTurn == blackTeam) {
            return whiteTeam;
        }
        return blackTeam;
    }

    public void changeTurn() {
        currentTurn = getEnemy();
    }

    public Map<Position, Piece> generateChessBoard() {
        final Map<Position, Piece> chessBoard = blackTeam.getPiecePosition();
        chessBoard.putAll(whiteTeam.getPiecePosition());
        return Collections.unmodifiableMap(chessBoard);
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
