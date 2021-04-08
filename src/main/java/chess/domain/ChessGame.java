package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;

import java.util.Collections;
import java.util.Map;

public class ChessGame {
    public static final int BLACK_TEAM = 0;
    public static final int WHITE_TEAM = 1;

    private final BlackTeam blackTeam;
    private final WhiteTeam whiteTeam;
    private Team currentTurn;
    private boolean isEnd;

    public ChessGame(final BlackTeam blackTeam, final WhiteTeam whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;

        blackTeam.setEnemy(whiteTeam);
        whiteTeam.setEnemy(blackTeam);

        this.currentTurn = this.whiteTeam;
        this.currentTurn.startTurn();
        this.isEnd = false;
    }

    public ChessGame(final BlackTeam blackTeam, final WhiteTeam whiteTeam, Team currentTurn, boolean isEnd) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurn = currentTurn;
        this.isEnd = isEnd;
    }

    public boolean havePieceInCurrentTurn(final Position position) {
        return currentTurn.havePiece(position);
    }

    public boolean move(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurn.choosePiece(current);
        if (!validateMovable(current, destination, chosenPiece)) {
            return false;
        }

        Team enemy = currentTurn.getEnemy();
        if (enemy.havePiece(destination)) {
            killEnemyPiece(destination, enemy);
        }

        currentTurn.move(current, destination);
        changeTurn();
        return true;
    }

    private boolean validateMovable(final Position current, final Position destination, final Piece chosenPiece) {
        if (currentTurn.havePiece(destination) || !chosenPiece.isMovable(current, destination, generateChessBoard())) {
            return false;
        }
        return true;
    }


    private void killEnemyPiece(Position destination, Team enemy) {
        Piece piece = enemy.killPiece(destination);
        checkMate(piece);
    }

    private void checkMate(Piece piece) {
        if (piece.isKing()) {
            finish();
        }
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public void finish() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void changeTurn() {
        currentTurn.endTurn();
        currentTurn = currentTurn.getEnemy();
        currentTurn.startTurn();
    }

    public Map<Position, Piece> generateChessBoard() {
        final Map<Position, Piece> chessBoard = blackTeam.getPiecePosition();
        chessBoard.putAll(whiteTeam.getPiecePosition());
        return Collections.unmodifiableMap(chessBoard);
    }

    public double calculateScoreByTeam(final int team) {
        if (team == BLACK_TEAM) {
            return blackTeam.calculateTotalScore();
        }

        if (team == WHITE_TEAM) {
            return whiteTeam.calculateTotalScore();
        }

        return 0;
    }

    public BlackTeam getBlackTeam() {
        return blackTeam;
    }

    public WhiteTeam getWhiteTeam() {
        return whiteTeam;
    }
}
