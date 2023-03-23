package chess.domain.command;

import java.util.List;
import java.util.Map;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.ChessGame;
import chess.domain.board.*;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.piece.Piece;

public class End implements CommandStatus {

    public End() {}

    @Override
    public CommandStatus start() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물을 움직일 수 없습니다.");
    }


    @Override
    public CommandStatus end() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 종료할 수 없습니다.");
    }

    @Override
    public CommandStatus printGameResult() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 게임 결과를 출력할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean canPrintGameResult() {
        return false;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Override
    public String getTurnDisplayName() {
        throw new IllegalStateException("[ERROR] 종료 상태에서는 턴 이름을 반환할 수 없습니다.");
    }

    @Override
    public ScoreBySideDto getScoreBySide() {
        throw new IllegalStateException("[ERROR] 종료 상태에서는 점수를 가져올 수 없습니다.");
    }

    @Override
    public GameResultBySideDto getGameResultBySide() {
        throw new IllegalStateException("[ERROR] 종료 상태에서는 결과를 가져올 수 없습니다.");
    }
}
