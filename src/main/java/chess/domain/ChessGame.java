package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.team.PiecePosition;
import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PAWN_DIRECTION = -1;
    private static final int BLACK_PIECE_COLUMN = 7;

    private static final int WHITE_PAWN_COLUMN = 1;
    private static final int WHITE_PAWN_DIRECTION = 1;
    private static final int WHITE_PIECE_COLUMN = 0;

    private final Team blackTeam;
    private final Team whiteTeam;
    private Team currentTurnTeam;
    private boolean isPlaying;

    public ChessGame() {
        this.blackTeam = initBlackTeam();
        this.whiteTeam = initWhiteTeam();
        this.currentTurnTeam = this.whiteTeam;
        this.isPlaying = true;
    }

    private Team initBlackTeam() {
        return new Team(new PiecePosition(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION, BLACK_PIECE_COLUMN));
    }

    private Team initWhiteTeam() {
        return new Team(new PiecePosition(WHITE_PAWN_COLUMN, WHITE_PAWN_DIRECTION, WHITE_PIECE_COLUMN));
    }

    public void move(final Position current, final Position destination) {
        validateMove(current, destination);
        currentTurnTeam.movePiece(current, destination);
        captureEnemy(destination);
    }

    private void validateMove(final Position current, final Position destination) {
        final Piece chosenPiece = currentTurnTeam.choosePiece(current);
        if (chosenPiece.isMovable(current, destination, generateChessBoard())) {
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
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
