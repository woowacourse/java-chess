package dto;

import domain.chessGame.ChessBoard;
import domain.piece.Piece;
import domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardStateDto {

    public static final String DOT = ".";
    
    private final List<String> boardState;

    public ChessBoardStateDto(ChessBoard chessBoard) {
        boardState = new ArrayList<>();

        for (List<Position> rowPositions : Position.getAllPositions()) {
            boardState.add(makeRowState(chessBoard.getChessBoard(), rowPositions));
        }
    }

    private String makeRowState(Map<Position, Piece> chessBoard, List<Position> rowPositions) {
        return rowPositions.stream()
                .map(position -> convertPieceNameOrDot(chessBoard, position))
                .collect(Collectors.joining());
    }

    private String convertPieceNameOrDot(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            return chessBoard.get(position).getName();
        }
        return DOT;
    }

    public List<String> getBoardState() {
        return boardState;
    }
}
