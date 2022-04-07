package chess.domain.game;

import chess.domain.event.Event;
import chess.domain.event.MoveCommand;
import chess.domain.game.statistics.GameResult;
import chess.dto.GameDto;
import chess.dto.board.ConsoleBoardViewDto;

public interface Game {

    Game play(Event event);

    Game moveChessmen(MoveCommand moveCommand);

    boolean isEnd();

    GameResult getResult();

    GameDto toDtoOf(int gameId);

    ConsoleBoardViewDto toConsoleView();
}
