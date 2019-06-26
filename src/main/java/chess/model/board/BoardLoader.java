package chess.model.board;

import chess.dto.BoardDto;
import chess.model.PlayerType;
import chess.model.Point;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.util.PointConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardLoader implements BoardInitializer {
    private Map<Point, Piece> pieces = new HashMap<>();

    @Override
    public Map<Point, Piece> initialize() {
        return pieces;
    }

    private void add(Point point, Piece piece) {
        pieces.put(point, piece);
    }

    public void convertBoardDtoToMap(List<BoardDto> boardDtos) {
        for (BoardDto boardDto : boardDtos) {
            Point point = PointConverter.convertToPoint(boardDto.getPoint());
            add(point, PieceFactory.create(
                    boardDto.getPiece(), PlayerType.valueOf(boardDto.getTeam()), point));
        }
    }
}
