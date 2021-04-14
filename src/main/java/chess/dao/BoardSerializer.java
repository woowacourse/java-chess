package chess.dao;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardSerializer {

    public String boardSerialize(Map<Position, Piece> chessBoard) {
        return chessBoard.entrySet()
            .stream()
            .map(entry -> entry.getValue().getName() + entry.getKey().getStringPosition())
            .collect(Collectors.joining());
    }


    public ChessBoard boardDeserialize(String response) {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (int i = 0; i < response.length(); i += 3) {
            char name = response.charAt(i);
            String position = response.substring(i + 1, i + 3);
            Position piecePosition = Position.of(position);

            Piece piece = PieceMapper.of(name);
            chessBoard.put(piecePosition, piece);
        }
        return new ChessBoard(chessBoard);
    }

}
