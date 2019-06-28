package chess.model.dao;

import chess.model.Square;
import chess.model.board.Board;
import chess.model.dto.BoardInfo;
import chess.model.dto.GameInfo;
import chess.model.unit.Side;

public interface ChessDao {
    static ChessDao getInstance() {
        return ChessDaoImp.getInstance();
    }

    void deleteAllData();

    void initializeBoard(BoardInfo boardInfo, GameInfo gameInfo);

    Side loadTurn();

    Board loadBoard();

    void updateMove(Square beginSquare, Square endSquare);

    void updateGameInfo(GameInfo gameInfo);

    boolean checkEmpty();
}