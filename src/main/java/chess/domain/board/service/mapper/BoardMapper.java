package chess.domain.board.service.mapper;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.board.position.Position;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.common.IndexCommand.PIECE_INDEX;
import static chess.common.IndexCommand.POSITION_COLUMN;
import static chess.common.IndexCommand.POSITION_INDEX;
import static chess.common.IndexCommand.POSITION_ROW;

public class BoardMapper {

    private static final String POSITION_AND_PIECE_DELIM = ":";
    private static final String PIECE_DELIM = ",";
    private static final String POSITION_DELIM = " ";

    public BoardRegisterRequest mapToBoardRegisterRequestFrom(final Board board) {

        // p:1 7,k:1 7

        final Map<Position, Piece> chessBoard = board.chessBoard();

        final String chessPosition = chessBoard.entrySet()
                                               .stream()
                                               .map(entry -> {
                                                   final Position position = entry.getKey();
                                                   return PieceInitialMapping.mapToPieceInitialFrom(entry.getValue())
                                                           + POSITION_AND_PIECE_DELIM
                                                           + position.column().value()
                                                           + POSITION_DELIM
                                                           + position.row().value();
                                               })
                                               .collect(Collectors.joining(PIECE_DELIM));

        return new BoardRegisterRequest(chessPosition, board.turn().color().name());
    }

    public Board mapToBoardSearchResponseFrom(final String position,
                                              final String turn, final Long id) {

        final Map<Position, Piece> chessBoard =
                Arrays.stream(position.split(PIECE_DELIM))
                      .map(s -> s.split(POSITION_AND_PIECE_DELIM))
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
                      ));


        return Board.bringBackPreviousGame(chessBoard, new Turn(Color.valueOf(turn)), id);
    }

}
