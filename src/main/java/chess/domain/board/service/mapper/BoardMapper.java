package chess.domain.board.service.mapper;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardMapper {

    public String mapToBoardPositionFrom(final Board board) {

        final Map<Position, Piece> chessBoard = board.chessBoard();

        return chessBoard.entrySet().stream()
                         .map(entry -> {
                             final Position position = entry.getKey();
                             return PieceInitialMapping.mapToPieceInitialFrom(entry.getValue())
                                     + " : "
                                     + position.column().value()
                                     + " "
                                     + position.row().value();
                         })
                         .collect(Collectors.joining(", "));
    }

    public Board mapToBoardMapFrom(final String position) {

        return new Board(Arrays.stream(position.split(","))
                               .map(s -> s.split(":"))
                               .collect(Collectors.toMap(
                                       arr -> new Position(
                                               Integer.parseInt(arr[1].trim().split(" ")[0]),
                                               Integer.parseInt(arr[1].trim().split(" ")[1])
                                       ),
                                       arr -> PieceInitialMapping.mapToPieceFrom(arr[0].trim())
                               )));
    }

}
