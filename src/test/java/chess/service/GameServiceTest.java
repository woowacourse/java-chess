package chess.service;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.fixture.PositionFixture.E4;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.MoveDto;
import chess.repository.GameDao;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GameServiceTest {

    private GameDao mockGameDao;

    @BeforeEach
    void setUp() {
        mockGameDao = new GameDao() {
            @Override
            public void save(final MoveDto moveDto, final int roomId) {
            }

            @Override
            public List<MoveDto> findAllByRoomId(final int roomId) {
                return List.of();
            }

            @Override
            public void deleteAll() {
            }
        };
    }

    private static List<PieceType> toPieceTypes(final Map<Position, Piece> board) {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .map(board::get)
                .map(Piece::type)
                .collect(toList());
    }

    @Test
    void 체스_게임을_생성한다() {
        // given
        final GameService gameService = new GameService(mockGameDao);
        final int roomId = 1;

        // when
        gameService.initialize(roomId);

        // then
        final List<PieceType> result = toPieceTypes(gameService.getResult(roomId).getBoard());
        assertThat(result).containsExactly(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    @Test
    void 기물을_움직인다() {
        // given
        final GameService gameService = new GameService(mockGameDao);
        final int roomId = 1;
        gameService.initialize(roomId);

        // when
        gameService.move(new MoveDto("e2", "e4"), roomId);

        // then
        final Map<Position, Piece> board = gameService.getResult(roomId).getBoard();
        assertThat(board.get(E4)).isEqualTo(Pawn.from(Color.WHITE));
    }

    @Test
    void 루이로페즈_모던_슈타이니츠_바리에이션_으로_게임을_진행한다() {
        // given
        final GameService gameService = new GameService(mockGameDao);
        final int roomId = 1;
        gameService.initialize(roomId);

        // when
        gameService.move(new MoveDto("e2", "e4"), roomId);
        gameService.move(new MoveDto("e7", "e5"), roomId);
        gameService.move(new MoveDto("g1", "f3"), roomId);
        gameService.move(new MoveDto("b8", "c6"), roomId);
        gameService.move(new MoveDto("f1", "b5"), roomId);
        gameService.move(new MoveDto("a7", "a6"), roomId);
        gameService.move(new MoveDto("b5", "a4"), roomId);
        gameService.move(new MoveDto("d7", "d6"), roomId);

        // then
        final List<PieceType> result = toPieceTypes(gameService.getResult(roomId).getBoard());
        assertThat(result).containsExactly(
                ROOK, EMPTY, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                EMPTY, PAWN, PAWN, EMPTY, EMPTY, PAWN, PAWN, PAWN,
                PAWN, EMPTY, KNIGHT, PAWN, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                BISHOP, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, KNIGHT, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, EMPTY, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY, EMPTY, ROOK
        );
    }

    @Test
    void 왕이_잡히는_경우_게임이_종료된다() {
        // given
        final GameService gameService = new GameService(mockGameDao);
        final int roomId = 1;
        gameService.initialize(roomId);
        gameService.move(new MoveDto("e2", "e4"), roomId);
        gameService.move(new MoveDto("e7", "e5"), roomId);
        gameService.move(new MoveDto("d1", "h5"), roomId);
        gameService.move(new MoveDto("f7", "f5"), roomId);

        // when
        gameService.move(new MoveDto("h5", "e8"), roomId);

        // then
        assertThat(gameService.isGameOver(roomId)).isTrue();
    }

    @Test
    void 보드를_삭제한다() {
        // given
        final GameService gameService = new GameService(mockGameDao);
        final int roomId = 1;
        gameService.initialize(roomId);
        gameService.move(new MoveDto("e2", "e4"), roomId);
        gameService.move(new MoveDto("e7", "e5"), roomId);
        gameService.move(new MoveDto("d1", "h5"), roomId);
        gameService.move(new MoveDto("f7", "f5"), roomId);
        gameService.move(new MoveDto("h5", "e8"), roomId);

        // when
        gameService.removeBoard(roomId);

        // then
        assertThatThrownBy(() -> gameService.move(new MoveDto("h5", "e8"), roomId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 찾을 수 없습니다.");
    }
}
