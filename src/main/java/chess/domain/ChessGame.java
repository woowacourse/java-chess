package chess.domain;

import chess.controller.GameStatus;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(Camp firstCamp, CampSwitcher campSwitcher) {
        this.gameStatus = GameStatus.READY;
        this.chessBoard = new ChessBoard(firstCamp, campSwitcher);
    }

    // TODO 상태검증 로직 통일
    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
    }

    public void move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
    }

    public void end(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.READY;
    }

    public Map<Position, Piece> readBoard() {
        if (gameStatus == GameStatus.READY) {
            throw new IllegalArgumentException("게임이 실행 중이지 않아 보드를 조회할 수 없습니다.");
        }
        return chessBoard.piecesByPosition();
    }

}
