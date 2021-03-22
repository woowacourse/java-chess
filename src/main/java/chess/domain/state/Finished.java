package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Finished implements GameState{
    private final Map<Position, Piece> chessBoard;

    public Finished(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new IllegalArgumentException("게임이 종료되었으므로 움직일 수 없습니다.");
    }

    @Override
    public Result result() {
        return null;
    }

    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public boolean containsKey(Position position) {
        return chessBoard.containsKey(position);
    }

    @Override
    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }

    @Override
    public void put(Position position, Piece piece) {

    }

    @Override
    public boolean isEnemy(Position position) {
        return false;
    }

}
