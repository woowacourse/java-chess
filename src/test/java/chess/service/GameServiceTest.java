package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.GameManager;
import chess.domain.board.Piece;
import chess.domain.board.PieceFactory;
import chess.domain.position.Position;
import chess.domain.state.Turn;
import chess.dto.ChessDto;
import chess.dto.MoveDto;
import chess.dto.PieceDto;
import chess.dto.PositionDto;
import chess.fixture.FakePieceDao;
import chess.fixture.FakeTurnDao;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {


    @DisplayName("새로운 게임을 시작한다. 게임의 초기 상태를 표현하는 dto를 반환한다")
    @Test
    void testStart() {
        // arrange
        Map<Position, Piece> initValue = PieceFactory.createInitializedChessBoard();
        Map<PositionDto, PieceDto> initialBoardDto = generateBoardDto(initValue);
        GameService gameService = new GameService(new GameManager(), new FakePieceDao(), new FakeTurnDao());

        // act
        ChessDto chessDto = gameService.start();

        // assert
        assertThat(chessDto.getBoard()).isEqualTo(initialBoardDto);
        assertThat(chessDto.isEnd()).isFalse();
        assertThat(chessDto.getCurrentTurn()).isEqualTo("WHITE_TURN");
    }

    @DisplayName("기물을 이동한다")
    @Test
    void testMove() {
        // arrange
        Map<Position, Piece> initValue = PieceFactory.createInitializedChessBoard();
        String source = "a2";
        String destination = "a4";
        Piece piece = initValue.get(Position.of(source));
        initValue.remove(Position.of(source));
        initValue.put(Position.of(destination), piece);
        Map<PositionDto, PieceDto> movedBoardDto = generateBoardDto(initValue);

        MoveDto moveDto = new MoveDto(source, destination);
        GameService gameService = new GameService(new GameManager(), new FakePieceDao(), new FakeTurnDao());

        // act
        ChessDto chessDto = gameService.move(moveDto);

        // assert
        assertThat(chessDto.getBoard()).isEqualTo(movedBoardDto);
        assertThat(chessDto.isEnd()).isFalse();
        assertThat(chessDto.getCurrentTurn()).isEqualTo("BLACK_TURN");
    }

    @DisplayName("이전에 저장된 게임을 불러온다")
    @Test
    void testLoad() {

        // arrange
        Map<Position, Piece> initValue = PieceFactory.createInitializedChessBoard();
        String source = "a2";
        String destination = "a4";
        Piece piece = initValue.get(Position.of(source));
        initValue.remove(Position.of(source));
        initValue.put(Position.of(destination), piece);

        Map<PositionDto, PieceDto> loadedBoardDto = generateBoardDto(initValue);

        PieceDao pieceDao = new FakePieceDao();
        pieceDao.savePieces(initValue);

        TurnDao turnDao = new FakeTurnDao();
        turnDao.updateTurn(Turn.WHITE_TURN, Turn.BLACK_TURN);

        GameService gameService = new GameService(new GameManager(), pieceDao, turnDao);

        // act
        ChessDto chessDto = gameService.load();

        // assert
        assertThat(chessDto.getBoard()).isEqualTo(loadedBoardDto);
        assertThat(chessDto.isEnd()).isFalse();
        assertThat(chessDto.getCurrentTurn()).isEqualTo("BLACK_TURN");
    }

    private Map<PositionDto, PieceDto> generateBoardDto(Map<Position, Piece> board) {
        Map<PositionDto, PieceDto> result = new HashMap<>();
        Set<Position> positions = board.keySet();
        for (Position position : positions) {
            Piece piece = board.get(position);
            result.put(PositionDto.from(position), PieceDto.from(piece));
        }
        return result;
    }
}
