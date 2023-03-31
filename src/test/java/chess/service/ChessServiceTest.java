package chess.service;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.GameStatus;
import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import chess.dto.GameInfoDto;
import chess.dto.PieceInfoDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {
    private GameDao mockGameDao;
    private PieceDao mockPieceDao;

    private ChessService chessService;

    @BeforeEach
    void setUp() {
        mockGameDao = new GameDaoStub();
        mockPieceDao = new PieceDaoStub();
        chessService = new ChessService(mockGameDao, mockPieceDao);
    }

    @Test
    @DisplayName("참여할 수 있는 방의 목록을 반환한다")
    void findAllPossibbleGameIds() {
        ChessGame possibleGame1 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame possibleGame2 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame impossibleGame1 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame impossibleGame2 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);

        chessService.start(1, possibleGame1);
        chessService.start(2, possibleGame2);
        chessService.start(3, impossibleGame1);
        chessService.start(4, impossibleGame2);
        chessService.end(3, impossibleGame1);
        chessService.end(4, impossibleGame2);

        List<Integer> possibleGameIds = chessService.findPossibleGameIds();
        assertThat(possibleGameIds).containsExactly(1, 2);
    }

    @Test
    @DisplayName("참여할 수 없는 방의 목록을 반환한다")
    void findAllImpossibleGameIds() {
        ChessGame possibleGame1 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame possibleGame2 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame impossibleGame1 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame impossibleGame2 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);

        chessService.start(1, possibleGame1);
        chessService.start(2, possibleGame2);
        chessService.start(3, impossibleGame1);
        chessService.start(4, impossibleGame2);
        chessService.end(3, impossibleGame1);
        chessService.end(4, impossibleGame2);

        List<Integer> possibleGameIds = chessService.findImpossibleGameIds();
        assertThat(possibleGameIds).containsExactly(3, 4);
    }

    @Test
    @DisplayName("이미 진행중인 방에 참가한다")
    void loadChessGame() {
        ChessGame possibleGame1 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);
        ChessGame possibleGame2 = ChessGame.load(Board.create(), Color.WHITE, GameStatus.START);

        chessService.start(1, possibleGame1);
        chessService.start(2, possibleGame2);

        ChessGame chessGame = chessService.loadChessGame(1);
        assertThat(chessGame.getTurn()).isEqualTo(Color.WHITE);
        assertThat(chessGame.getStatus()).isEqualTo(GameStatus.MOVE);
    }

    @Test
    @DisplayName("새로운 방에 참가한다")
    void loadChessGame2() {
        ChessGame chessGame = chessService.loadChessGame(1);
        assertThat(chessGame.getTurn()).isEqualTo(Color.WHITE);
        assertThat(chessGame.getStatus()).isEqualTo(GameStatus.START);
    }

    @Test
    @DisplayName("start 테스트")
    void start() {
        ChessGame chessGame = ChessGame.create();
        chessService.start(1, chessGame);

        GameInfoDto idOne = mockGameDao.findById(1);
        assertThat(idOne.getStatus()).isEqualTo(GameStatus.MOVE);
        assertThat(idOne.getTurn()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("move 테스트")
    void move() {
        ChessGame chessGame = ChessGame.create();
        chessService.start(1, chessGame);
        chessService.move(1, chessGame, List.of("a2", "a4"));

        GameInfoDto idOne = mockGameDao.findById(1);
        assertThat(idOne.getStatus()).isEqualTo(GameStatus.MOVE);
        assertThat(idOne.getTurn()).isEqualTo(Color.BLACK);

        List<PieceInfoDto> piecesInIdOne = mockPieceDao.findAllById(1);
        PieceInfoDto a2 = piecesInIdOne.stream()
                .filter(pieceInfoDto -> pieceInfoDto.getPosition().equals(Position.from("a2")))
                .findAny()
                .get();
        PieceInfoDto a4 = piecesInIdOne.stream()
                .filter(pieceInfoDto -> pieceInfoDto.getPosition().equals(Position.from("a4")))
                .findAny()
                .get();

        assertThat(a2.getPiece()).isEqualTo(Empty.create(Color.NONE));
        assertThat(a4.getPiece()).isEqualTo(Pawn.create(Color.WHITE));
    }

    @Test
    @DisplayName("end 테스트")
    void end() {
        ChessGame startGame = ChessGame.create();
        chessService.start(1, startGame);

        ChessGame endGame = ChessGame.load(Board.create(), Color.BLACK, GameStatus.MOVE);
        chessService.end(1, endGame);

        GameInfoDto idOne = mockGameDao.findById(1);
        assertThat(idOne.getStatus()).isEqualTo(GameStatus.END);
        assertThat(idOne.getTurn()).isEqualTo(Color.BLACK);
    }

    private class GameDaoStub implements GameDao {
        private final Map<Integer, GameInfoDto> games = new HashMap<>();

        @Override
        public List<Integer> findAllPossibleId() {
            return games.values().stream()
                    .filter(gameInfoDto -> gameInfoDto.getStatus() != GameStatus.END)
                    .filter(gameInfoDto -> gameInfoDto.getStatus() != GameStatus.CATCH)
                    .map(GameInfoDto::getId)
                    .collect(toList());
        }

        @Override
        public List<Integer> findAllImpossibleId() {
            return games.values().stream()
                    .filter(gameInfoDto -> gameInfoDto.getStatus() == GameStatus.END
                            || gameInfoDto.getStatus() == GameStatus.CATCH)
                    .map(GameInfoDto::getId)
                    .collect(toList());
        }

        @Override
        public GameInfoDto findById(int gameId) {
            return games.get(gameId);
        }

        @Override
        public void create(GameInfoDto gameInfoDto) {
            games.put(gameInfoDto.getId(), gameInfoDto);
        }

        @Override
        public void updateById(GameInfoDto gameInfoDto) {
            games.put(gameInfoDto.getId(), gameInfoDto);
        }

        @Override
        public void deleteById(int gameId) {
            games.remove(gameId);
        }

        @Override
        public void deleteAll() {
            games.clear();
        }
    }

    private class PieceDaoStub implements PieceDao {
        Map<Integer, List<PieceInfoDto>> pieces = new HashMap<>();

        @Override
        public List<PieceInfoDto> findAllById(int gameId) {
            return pieces.get(gameId);
        }

        @Override
        public void create(int gameId, PieceInfoDto pieceInfoDto) {
            List<PieceInfoDto> pieces = this.pieces.getOrDefault(gameId, new ArrayList<>());
            pieces.add(pieceInfoDto);
            this.pieces.put(gameId, pieces);
        }

        @Override
        public void updateById(int gameId, PieceInfoDto pieceInfoDto) {
            List<PieceInfoDto> pieces = this.pieces.get(gameId);

            PieceInfoDto findPieceInfoDto = pieces.stream()
                    .filter(pieceInfo -> pieceInfo.getPosition().equals(pieceInfoDto.getPosition()))
                    .findAny()
                    .get();

            pieces.remove(findPieceInfoDto);
            pieces.add(pieceInfoDto);
        }

        @Override
        public void deleteById(int gameId) {
            pieces.remove(gameId);
        }

        @Override
        public void deleteAll() {
            pieces.clear();
        }
    }
}
