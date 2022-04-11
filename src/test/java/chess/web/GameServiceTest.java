package chess.web;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Piece;
import chess.domain.board.PieceFactory;
import chess.domain.position.Position;
import chess.web.dto.ChessDto;
import chess.web.dto.PieceDto;
import chess.web.dto.PositionDto;
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
        GameService gameService = new GameService(new FakePieceDao(), new FakeTurnDao());

        // act
        ChessDto chessDto = gameService.start();

        // assert
        assertThat(chessDto.getBoard()).isEqualTo(initialBoardDto);
        assertThat(chessDto.isEnd()).isFalse();
        assertThat(chessDto.getCurrentTurn()).isEqualTo("WHITE_TURN");
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
