package chess.web.dao;

import chess.domain.board.BoardGenerator;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.position.Position;

public class ChessGame {

    private String id;
    private String name;
    private boolean isEnd;
    private ChessBoard chessBoard;

    public ChessGame(BoardGenerator boardGenerator) {
        this.chessBoard = new ChessBoard(boardGenerator);
    }

    public static ChessGame initChessGame() {
        ChessGame chessGame = new ChessGame(new ChessBoardGenerator());
        return chessGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void execute(Movement movement) {
        chessBoard.move(
                Position.of(movement.getSource()),
                Position.of(movement.getTarget())
        );
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isGameSet() {
        return !chessBoard.checkKingExist();
    }
}
