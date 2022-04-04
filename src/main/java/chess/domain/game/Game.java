package chess.domain.game;

import chess.db.entity.FullGameEntity;
import chess.dto.request.MoveCommandDto;
import chess.dto.response.board.ConsoleBoardViewDto;
import chess.dto.response.board.WebBoardViewDto;

public interface Game {

    Game init();

    Game moveChessmen(MoveCommandDto moveCommand);

    boolean isEnd();

    GameResult getResult();

    GameState getState();

    FullGameEntity toEntityOf(int id);

    ConsoleBoardViewDto toConsoleView();

    WebBoardViewDto toBoardWebView();
}
