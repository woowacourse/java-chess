package chess.controller;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessWebController {

    final Board board = new Board();

    public Map<String, String> start() {
        board.init();
        Map<Position, Piece> pieces = board.pieces();
        return PositionToStringMap(pieces);
    }

    private Map<String, String> PositionToStringMap(Map<Position, Piece> pieces) {
        Map<String, String> boardInfo = new HashMap<>();
        for (Position position : pieces.keySet()) {
            boardInfo.put(position.positionToString(), getSymbol(pieces.get(position)));
        }
        boardInfo.put("isFinish", String.valueOf(board.isFinish()));
        boardInfo.put("turn", board.turn().color());
        return boardInfo;
    }

    private String getSymbol(Piece piece) {
        if (Objects.isNull(piece)) {
            return ".";
        }
        return piece.symbol();
    }

    public Map<String, String> movedPiece(String source, String target) {
        try {
            board.movePiece(source, target);
        } catch (Exception e) {
            System.out.println("이동실패");
        }
        Map<Position, Piece> pieces = board.pieces();
        return PositionToStringMap(pieces);
    }

    public List<String> movablePositions(String source) {
        List<String> positions = new ArrayList<>();
        try {
            for (Position position : board.movablePositions(source)) {
                positions.add(position.positionToString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return positions;
    }
}
