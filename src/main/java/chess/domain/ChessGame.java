package chess.domain;

import chess.domain.piece.ChessPiece;

public class ChessGame {
    private final ChessMen blackChessMen;
    private final ChessMen whiteChessMen;
    private Team turn;

    private ChessGame(ChessMen blackChessMen, ChessMen whiteChessMen) {
        this.blackChessMen = blackChessMen;
        this.whiteChessMen = whiteChessMen;
        this.turn = Team.WHITE;
    }

    public static ChessGame create() {
        ChessMen blackChessMen = ChessMen.create(Team.BLACK);
        ChessMen whiteChessMen = ChessMen.create(Team.WHITE);
        return new ChessGame(blackChessMen, whiteChessMen);
    }

    public ChessMen getBlackChessMen() {
        return blackChessMen;
    }

    public ChessMen getWhiteChessMen() {
        return whiteChessMen;
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = getChessPiece(sourcePosition);

        if (!chessPiece.isMovable(targetPosition, whiteChessMen, blackChessMen)) {
            throw new IllegalArgumentException("[ERROR] 경로에 다른 체스가 있어 이동할 수 없습니다.");
        }

        if (chessPiece.myTeamExistsInTargetPosition(targetPosition, getMyTeam())) {
            throw new IllegalArgumentException("[ERROR] 도착 위치에 우리 팀 체스피스가 있어 이동할 수 없습니다.");
        }

        ChessMen enemy = getEnemy();
        if (chessPiece.enemyExistsInTargetPosition(targetPosition, enemy)) {
            enemy.removeChessPieceAt(targetPosition);
        }

        chessPiece.move(targetPosition);
        turn = turn.reverse();
    }

    private ChessMen getMyTeam() {
        if (turn.isWhite()) {
            return whiteChessMen;
        }
        return blackChessMen;
    }

    private ChessMen getEnemy() {
        if (turn.isWhite()) {
            return blackChessMen;
        }
        return whiteChessMen;
    }

    private ChessPiece getChessPiece(ChessBoardPosition sourcePosition) {
        if (turn.isSame(Team.WHITE)) {
            return whiteChessMen.getChessPieceAt(sourcePosition);
        }
        return blackChessMen.getChessPieceAt(sourcePosition);
    }

    public boolean isGameEnd() {
        return blackChessMen.isKingDead() || whiteChessMen.isKingDead();
    }

    public Team judgeWinner() {
        if (blackChessMen.calculateScore() > whiteChessMen.calculateScore()) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }
}
