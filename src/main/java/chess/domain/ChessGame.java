package chess.domain;

import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private TeamColor teamColor;
    private boolean isPlaying;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
        this.isPlaying = true;
    }

    public void move(final Position source, final Position dest) {
        chessBoard.move(source, dest, teamColor);
        if (chessBoard.isKingDead()) {
            isPlaying = false;
        }
        teamColor = teamColor.transfer();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
