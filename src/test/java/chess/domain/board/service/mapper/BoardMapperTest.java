package chess.domain.board.service.mapper;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.board.service.mapper.PieceInitialMapping;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.pawn.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardMapperTest {

    private final BoardMapper boardMapper = new BoardMapper();

    @Test
    @DisplayName("mapToBoardPositionFrom() : Board 를 위치를 가지는 String으로 mapping 시킬 수 있다. ")
    void test_mapToBoardDaoFrom() throws Exception {
        //given
        final Board board = new Board(
                Map.of(new Position(1, 7), new King(Color.BLACK),
                       new Position(3, 7), new Pawn(Color.BLACK),
                       new Position(4, 7), new King(Color.WHITE),
                       new Position(5, 7), new Pawn(Color.WHITE)
                ));

        //when
        //"K : 1 7, P : 3 7, k : 4 7, p : 5 7"
        final String chessBoardPosition = boardMapper.mapToBoardPositionFrom(board);

        Map<Position, Class<?>> resultMap =

                Arrays.stream(chessBoardPosition.split(","))
                      .map(s -> s.split(":"))
                      .collect(Collectors.toMap(
                              arr -> new Position(
                                      Integer.parseInt(arr[1].trim().split(" ")[0]),
                                      Integer.parseInt(
                                              arr[1].trim().split(" ")[1])),
                              arr -> PieceInitialMapping.mapToClassTypeFrom(arr[0].trim())
                      ));

        //then
        assertAll(
                () -> assertEquals(4, resultMap.size()),
                () -> assertEquals(resultMap.get(new Position(1, 7)), King.class),
                () -> assertEquals(resultMap.get(new Position(3, 7)), Pawn.class),
                () -> assertEquals(resultMap.get(new Position(5, 7)), Pawn.class),
                () -> assertEquals(resultMap.get(new Position(4, 7)), King.class)
        );
    }

    @Test
    @DisplayName("mapToBoardMapFrom() : 위치를 가지고 있는 String을 Board로 변환시킬 수 있다.")
    void test_mapToBoardMapFrom() throws Exception {
        //given
        //"K : 1 7, P : 3 7, k : 4 7, p : 5 7"
        final String position = "K : 1 7, P : 3 7, k : 4 7, p : 5 7";

        //when
        Board board = boardMapper.mapToBoardMapFrom(position);

        //then
        final Map<Position, Piece> chessBoard = board.chessBoard();

        Assertions.assertThat(chessBoard)
                  .hasSize(4)
                  .containsKeys(new Position(1, 7),
                                new Position(3, 7),
                                new Position(5, 7),
                                new Position(4, 7));
    }
}

