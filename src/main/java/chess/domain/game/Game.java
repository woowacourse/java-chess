package chess.domain.game;

import chess.domain.event.Event;
import chess.domain.game.statistics.GameResult;
import chess.dto.GameDto;
import chess.dto.board.ConsoleBoardViewDto;

public interface Game {

    Game play(Event event);

    boolean isEnd();

    GameResult getResult();

    GameDto toDtoOf(int gameId);

    ConsoleBoardViewDto toConsoleView();
}
