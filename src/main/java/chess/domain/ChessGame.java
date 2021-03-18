package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;

import java.util.Collections;
import java.util.Map;

public class ChessGame {
    private Team currentTurn;
    private BlackTeam blackTeam;
    private WhiteTeam whiteTeam;

    public ChessGame(BlackTeam blackTeam, WhiteTeam whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.currentTurn = this.blackTeam;
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
        }

        currentTurn.move(current, destination);
        chosenPiece.isMoved();
    }

    private Team getEnemy() {
        if (currentTurn == blackTeam) {
            return whiteTeam;
        }
        return blackTeam;
    }

    public Map<Position, Piece> generateChessBoard() {
        final Map<Position, Piece> chessBoard = blackTeam.getPiecePosition();
        chessBoard.putAll(whiteTeam.getPiecePosition());
        return Collections.unmodifiableMap(chessBoard);
    }

    public BlackTeam getBlackTeam() {
        return blackTeam;
    }

    public WhiteTeam getWhiteTeam() {
        return whiteTeam;
    }
}
