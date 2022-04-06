package chess.domain.game;

import chess.domain.event.MoveCommand;
import chess.dto.response.GameDto;
import chess.dto.response.board.ConsoleBoardViewDto;

public interface Game {

    Game init();

    Game moveChessmen(MoveCommand moveCommand);

    boolean isEnd();

    GameResult getResult();

    GameDto toDtoOf(int gameId);

    ConsoleBoardViewDto toConsoleView();
}
