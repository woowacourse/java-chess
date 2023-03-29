package chess.controller.command.command;

import chess.controller.ChessState;
import chess.dao.ChessGameDao;
import chess.dao.ChessPositionDao;
import chess.dao.MoveLogDao;
import chess.domain.board.Board;
import chess.domain.board.initial.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.ChessPositionDto;
import chess.view.OutputView;

import java.util.List;
import java.util.Set;

import static chess.controller.ChessState.END;
import static chess.controller.ChessState.PROGRESS;
import static chess.controller.ChessState.START;

public class MoveCommand implements Command {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final String CANNOT_MOVE_BEFORE_START_ERROR_MESSAGE = "게임을 시작하전에 체스 기물을 이동할 수 없습니다";

    private final Position source;
    private final Position target;

    private MoveCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveCommand from(final List<String> inputs) {
        final Position source = Position.from(inputs.get(SOURCE_INDEX));
        final Position target = Position.from(inputs.get(TARGET_INDEX));

        return new MoveCommand(source, target);
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGameDto chessGameDto) {
        if (!Set.of(START, PROGRESS).contains(state)) {
            throw new IllegalArgumentException(CANNOT_MOVE_BEFORE_START_ERROR_MESSAGE);
        }

        final Board loadBoard = MoveLogDao.load(chessGameDto, BoardFactory.create());
        final ChessGame chessGame = ChessGame.of(loadBoard, chessGameDto.getTurn());

        final Piece sourcePiece = loadBoard.getPiece(source);
        final Piece targetPiece = loadBoard.getPiece(target);
        chessGame.move(source, target);

        final ChessPositionDto sourceChessPositionDto = ChessPositionDao.findByPositionAndPiece(source, sourcePiece);
        final ChessPositionDto targetChessPositionDto = ChessPositionDao.findByPositionAndPiece(target, targetPiece);
        MoveLogDao.insertMove(chessGameDto, sourceChessPositionDto, targetChessPositionDto);

        OutputView.printBoard(chessGame.getBoard());
        return existOpponentKing(chessGame, chessGameDto);
    }

    private ChessState existOpponentKing(final ChessGame chessGame, final ChessGameDto chessGameDto) {
        if (chessGame.isExistOpponentKing()) {
            chessGame.changeTurn();
            ChessGameDao.updateTurn(chessGameDto.getId(), chessGame);
            return PROGRESS;
        }
        OutputView.printResultWinning(chessGame.getTurn());
        return END;
    }
}
