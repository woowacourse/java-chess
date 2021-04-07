package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Players;
import chess.domain.piece.Color;

public class WebSimpleBoardDTO {

    private int boardId;
    private String whitePlayer;
    private String blackPlayer;
    private boolean isWhiteTurn;
    private boolean isFinish;

    public WebSimpleBoardDTO() {}

    public WebSimpleBoardDTO(Board board) {
        this(board, 0);
    }

    public WebSimpleBoardDTO(Board board, int boardId) {
        Players players = board.players();
        this.whitePlayer = players.getWhitePlayer();
        this.blackPlayer = players.getBlackPlayer();
        this.isFinish = board.isFinish();
        this.isWhiteTurn = board.turn() == Color.WHITE;
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

    public void setWhiteTurn(boolean whiteTurn) {
        this.isWhiteTurn = whiteTurn;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public boolean isFinish() {
        return isFinish;
    }
}
