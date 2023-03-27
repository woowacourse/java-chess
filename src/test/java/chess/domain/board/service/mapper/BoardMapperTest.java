package chess.domain.board.service.mapper;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.board.position.Position;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.pawn.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardMapperTest {

    private final BoardMapper boardMapper = new BoardMapper();

    @Test
    @DisplayName("mapToBoardRegisterRequestFrom() : Board 를 위치를 가지는 String으로 mapping 시킬 수 있다. ")
    void test_mapToBoardRegisterRequestFrom() throws Exception {
        //given
        final Board board = Board.bringBackPreviousGame(
                Map.of(new Position(1, 7), new King(Color.BLACK),
                       new Position(3, 7), new Pawn(Color.BLACK),
                       new Position(4, 7), new King(Color.WHITE),
                       new Position(5, 7), new Pawn(Color.WHITE)
                ), new Turn(Color.WHITE));

        //when
        //"K : 1 7, P : 3 7, k : 4 7, p : 5 7"
        final BoardRegisterRequest boardRegisterRequest =
                boardMapper.mapToBoardRegisterRequestFrom(board);

        Map<Position, Class<?>> resultMap =

                Arrays.stream(boardRegisterRequest.position().split(","))
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
                () -> assertEquals(resultMap.get(new Position(4, 7)), King.class),
                () -> assertEquals(boardRegisterRequest.turn(), Color.WHITE.name())
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

        assertThat(chessBoard)
                  .hasSize(4)
                  .containsKeys(new Position(1, 7),
                                new Position(3, 7),
                                new Position(5, 7),
                                new Position(4, 7));
    }

    @Test
    @DisplayName("mapToBoardSearchResponseFrom() : 위치와 차례를 String을 Board로 변환시킬 수 있다.")
    void test_mapToBoardSearchResponseFrom() throws Exception {
        //given
        //"K : 1 7, P : 3 7, k : 4 7, p : 5 7"
        final String position = "K : 1 7, P : 3 7, k : 4 7, p : 5 7";
        final String turn = "WHITE";

        //when
        Board board = boardMapper.mapToBoardSearchResponseFrom(position, turn);

        //then
        final Map<Position, Piece> chessBoard = board.chessBoard();
        final Turn savedTurn = board.turn();

        assertAll(
                () -> assertThat(chessBoard)
                        .hasSize(4)
                        .containsKeys(new Position(1, 7),
                                      new Position(3, 7),
                                      new Position(5, 7),
                                      new Position(4, 7)),

                () -> assertEquals(savedTurn, new Turn(Color.WHITE))
        );
    }
}

