package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.position.Position;
import chess.domain.piece.Piece;

public interface CommandStatus {

    CommandStatus start();

    CommandStatus move(Position sourcePosition, Position targetPosition);

    CommandStatus end();

    CommandStatus printGameResult();

    boolean isExistPreviousGame(Long gameId);

    boolean isEnd();

    boolean canPrintGameResult();

    List<Piece> getPieces();

    String getTurnDisplayName();

    ScoreBySideDto getScoreBySide();

    GameResultBySideDto getGameResultBySide();
}
