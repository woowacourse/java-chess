package chess.domain.manager;

import chess.controller.web.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

public interface ChessGameManager {
    ChessGameManager start();

    ChessGameManager end();

    void move(Position from, Position to);

    Board getBoard();

    Color nextColor();

    boolean isNotEnd();

    boolean isEnd();

    boolean isStart();

    boolean isKingDead();

    long getId();

    Map<String, PieceDto> getPieces();

    ChessGameStatistics getStatistics();
}
