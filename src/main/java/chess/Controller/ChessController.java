package chess.Controller;

import chess.Controller.command.ParsedCommand;
import chess.Controller.command.PieceCommandFactory;
import chess.Controller.command.ScoreCommandFactory;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.domain.board.Board;

public class ChessController {

    private final Board board;

    public ChessController(Board board) {
        this.board = board;
    }

    public PiecesDto doActionAboutPieces(final ParsedCommand parsedCommand) {
        // 디비에서 필요한 정보를 가져온다 -
        // 가져온 정보로 도메인 객체를 만든다 -
        // 도메인 객체를 호출한다
        // 도메인 객체가 만든 결과를 DTO로 만든다
        // 만들어진 DTO를 가지고 DB 레코드를 수정한다
        // DTO를 반환한다.
        return PieceCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, board);
    }

    public PiecesDto getPieces() {
        return PiecesDto.fromEntity(board);
    }

    public ScoreDto doActionAboutScore(final ParsedCommand parsedCommand) {
        return ScoreCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, board);
    }

    public StateDto getCurrentStatus() {
        return StateDto.fromEntity(board);
    }
}
