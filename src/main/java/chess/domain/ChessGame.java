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
        this.isEnd = false;
    }

    public boolean havePieceInCurrentTurn(final Position position) {
        return currentTurn.havePiece(position);
    }

    public boolean move(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurn.choosePiece(current);
        validateMovable(current, destination, chosenPiece);

        Team enemy = currentTurn.getEnemy();
        if (enemy.havePiece(destination)) {
            killEnemyPiece(destination, enemy);
        }

        currentTurn.move(current, destination);
        changeTurn();
        return true;
    }

    private void validateMovable(final Position current, final Position destination, final Piece chosenPiece) {
        if (currentTurn.havePiece(destination) || !chosenPiece.isMovable(current, destination, generateChessBoard())) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
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

    private void finish() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void changeTurn() {
        currentTurn = currentTurn.getEnemy();
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
