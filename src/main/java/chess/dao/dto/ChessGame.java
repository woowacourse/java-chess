package chess.dao.dto;

import chess.converter.PiecesConverter;
import chess.domain.manager.ChessGameManager;
import chess.domain.piece.attribute.Color;

public class ChessGame {
    private final long id;
    private final Color nextTurn;
    private final boolean running;
    private final String pieces;

    public ChessGame(ChessGameManager chessGameManager) {
        this.id = chessGameManager.getId();
        this.nextTurn = chessGameManager.nextColor();
        this.running = chessGameManager.isStart() && chessGameManager.isNotEnd();
        this.pieces = PiecesConverter.convertString(chessGameManager.getBoard());
    }

    public ChessGame(long id, Color nextTurn, boolean running, String pieces) {
        this.id = id;
        this.nextTurn = nextTurn;
        this.running = running;
        this.pieces = pieces;
    }

    public Long getId() {
        return id;
    }

    public Color getNextTurn() {
        return nextTurn;
    }

    public boolean isRunning() {
        return running;
    }

    public String getPieces() {
        return pieces;
    }
}
