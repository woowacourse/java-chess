package chess.service.util;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dao.entity.BoardEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardEntitiesToBoardConvertor {

    public static Board convert(final List<BoardEntity> boardEntities) {
        Map<Position, Piece> board = new HashMap<>();
        for (BoardEntity boardEntity : boardEntities) {
            Position position = Position.of(
                    boardEntity.getPositionColumnValue().charAt(0),
                    boardEntity.getPositionRowValue()
            );
            Piece piece = StringToPieceConvertor.convert(boardEntity.getPieceName(), boardEntity.getPieceTeamValue());
            board.put(position, piece);
        }
        return new Board(board);
    }
}
