package chess.domain;

import chess.domain.piece.ChessPiece;

public class ChessGame {
    private static final String NOT_MOVABLE_PATH_EXCEPTION = "[ERROR] 체스피스가 이동할 수 없는 위치입니다.";
    private static final String MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION = "[ERROR] 도착 위치에 우리 팀 체스피스가 있어 이동할 수 없습니다.";

    private final ChessBoard chessBoard;
    private Team turn;

    private ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.turn = Team.WHITE;
    }

    public static ChessGame create() {
        ChessBoard chessBoard = ChessBoard.create();
        return new ChessGame(chessBoard);
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = chessBoard.getChessPiece(sourcePosition, turn);

        if (!chessPiece.isMovable(sourcePosition, targetPosition, chessBoard)) {
            throw new IllegalArgumentException(NOT_MOVABLE_PATH_EXCEPTION);
        }

        if (chessBoard.existChessPieceOf(targetPosition, turn)) {
            throw new IllegalArgumentException(MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION);
        }

        if (chessBoard.existChessPieceOf(targetPosition, turn.reverse())) {
            chessBoard.removeChessPieceAt(targetPosition);
        }

        chessBoard.move(sourcePosition, targetPosition);
        turn = turn.reverse();
    }

    public boolean end() {
        return chessBoard.isKingDead();
    }

    public Team judgeWinner() {
        double blackTeamScore = chessBoard.calculateScore(Team.BLACK);
        double whiteTeamScore = chessBoard.calculateScore(Team.WHITE);
        if (blackTeamScore == whiteTeamScore) {
            return Team.NONE;
        }
        if (blackTeamScore > whiteTeamScore) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
