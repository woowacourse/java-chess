package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public abstract class Game implements GameState {

    private final Map<Position, Piece> chessBoard;

    public Game(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }


    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }


}
