package chess.controller;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {
    private Board board = new Board();
    private GameState state = GameState.PREPARING;

    public boolean isPlayable() {
        return state.isPlayable();
    }

    public void initBoard() {
        if (state == GameState.PLAYING) {
            throw new IllegalStateException("이미 게임이 진행중입니다. 새로운 게임을 시작하려면 게임을 종료 후 재시작 해주세요");
        }
        board = new Board();
        state = GameState.PLAYING;
    }

    public void tryMove(Location source, Location target) {
        if(state != GameState.PLAYING){
            throw new IllegalStateException("게임이 시작되지 않았습니다. 게임을 시작해 주세요");
        }
        board.tryMove(source, target);
    }

    public void endGame() {
        state = GameState.END;
    }

    public Map<Location, Piece> getBoard() {
        return board.getBoard();
    }
}
