package chess.domain.board.service.mapper;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.common.IndexCommand.PIECE_INDEX;
import static chess.common.IndexCommand.POSITION_COLUMN;
import static chess.common.IndexCommand.POSITION_INDEX;
import static chess.common.IndexCommand.POSITION_ROW;

public class BoardMapper {

    private static final String POSITION_AND_PIECE_DELIM = " : ";
    private static final String PIECE_DELIM = ", ";
    private static final String POSITION_DELIM = " ";
    private static final String PIECE_REGEX = ",";
    private static final String POSITION_REGEX = ":";

    public String mapToBoardPositionFrom(final Board board) {

        final Map<Position, Piece> chessBoard = board.chessBoard();

        return chessBoard.entrySet().stream()
                         .map(entry -> {
                             final Position position = entry.getKey();
                             return PieceInitialMapping.mapToPieceInitialFrom(entry.getValue())
                                     + POSITION_AND_PIECE_DELIM
                                     + position.column().value()
                                     + POSITION_DELIM
                                     + position.row().value();
                         })
                         .collect(Collectors.joining(PIECE_DELIM));
    }

    public Board mapToBoardMapFrom(final String position) {

        return new Board(Arrays.stream(position.split(PIECE_REGEX))
                               .map(s -> s.split(POSITION_REGEX))
                               .collect(Collectors.toMap(
                                       arr -> {
                                           final String[] s = arr[POSITION_INDEX.value()].trim()
                                                                                         .split(POSITION_DELIM);

                                           return new Position(
                                                   Integer.parseInt(s[POSITION_COLUMN.value()]),
                                                   Integer.parseInt(s[POSITION_ROW.value()])
                                           );
                                       },
                                       arr -> PieceInitialMapping.mapToPieceFrom(
                                               arr[PIECE_INDEX.value()].trim())
                               )));
    }

}
