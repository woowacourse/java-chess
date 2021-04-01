package chess;

import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;

import java.util.Map;

public class ChessService {

    private static Game game = new Game();

    public ResponseDto move(RequestDto requestDto) {
        String from = requestDto.from();;
        String to = requestDto.to();
        try {
            game.move(Position.from(from), Position.from(to));
            if(!game.isNotEnd()) {
                return new ResponseDto("300", "끝", game.winner().getColor().name());
            }
            return new ResponseDto("200", "성공", game.currentPlayer().toString());
        } catch (Exception e) {
            return new ResponseDto("400", e.getMessage(), game.currentPlayer().toString());
        }
    }

    public Map<String, String> getCurrentBoard() {
        BoardDto boardDto = new BoardDto(game.getPieces());
        return boardDto.getBoard();
    }

    public void initChessBoard() {
        game = new Game();
    }
}
