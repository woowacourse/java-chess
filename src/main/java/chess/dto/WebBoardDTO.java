package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Players;
import chess.domain.piece.Color;

public class WebBoardDTO {

    private int boardId;
    private String whitePlayer;
    private String blackPlayer;
    private boolean turnIsWhite;
    private boolean isFinish;

    public WebBoardDTO() {

    }

    public WebBoardDTO(final Board board) {
        this(board, 0);
    }

    public WebBoardDTO(Board board, int boardId) {
        Players players = board.players();
        this.whitePlayer = players.getWhitePlayer();
        this.blackPlayer = players.getBlackPlayer();
        this.isFinish = board.isFinish();
        this.turnIsWhite = board.turn() == Color.WHITE;
        this.boardId = boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setWhitePlayer(String whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public void setTurnIsWhite(boolean turnIsWhite) {
        this.turnIsWhite = turnIsWhite;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public boolean getTurnIsWhite() {
        return turnIsWhite;
    }

    public boolean getIsFinish() {
        return isFinish;
    }

    public int getBoardId() {
        return boardId;
    }
}
