package chess.domain.game;

import chess.domain.board.piece.Color;
import chess.dto.response.ConsoleBoardViewDto;
import chess.dto.MoveCommandDto;
import chess.dto.response.WebBoardViewDto;
import chess.model.GameResult;

public interface Game {

    Game init();

    Color getCurrentTurnColor();

    Game moveChessmen(MoveCommandDto moveCommand);

    boolean isEnd();

    GameResult getResult();

    ConsoleBoardViewDto toConsoleView();

    WebBoardViewDto toBoardWebView();
}
