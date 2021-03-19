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

    private Team currentTurn;
    private BlackTeam blackTeam;
    private WhiteTeam whiteTeam;
    private boolean isEnd = false;

    public ChessGame(BlackTeam blackTeam, WhiteTeam whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurn = this.whiteTeam;
    }

    public void move(Position current, Position destination) {
        final Piece chosenPiece = currentTurn.choosePiece(current);
        if (currentTurn.havePiece(destination) || !chosenPiece.isMovable(current, destination, generateChessBoard())) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }

        Team enemy = getEnemy();
        if (enemy.havePiece(destination)) {
            Piece piece = enemy.killPiece(destination);
            currentTurn.catchPiece(piece);
            checkMate(piece);
        }

        currentTurn.move(current, destination);
        chosenPiece.isMoved();
    }

    private void checkMate(Piece piece) {
        if (piece.isKing()) {
            finish();
        }
    }

    public void finish() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
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
