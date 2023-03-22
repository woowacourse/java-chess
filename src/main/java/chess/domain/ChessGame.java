package chess.domain;

import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private TeamColor teamColor;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
    }

    public void move(final Position source, final Position dest) {
        chessBoard.move(source, dest, teamColor);
        teamColor = teamColor.transfer();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
