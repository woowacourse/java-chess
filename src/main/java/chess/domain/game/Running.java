package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveCommandDto;

abstract class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;
    protected static final String INVALID_TURN_EXCEPTION_FORMAT = "%s 진영이 움직일 차례입니다!";

    Running(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    public final Game moveChessmen(MoveCommandDto dto) {
        Piece sourcePiece = chessmen.findByPosition(sourcePositionOf(dto));
        Position targetPosition = targetPositionOf(dto);
        validateTurn(sourcePiece);

        if (chessmen.isOccupied(targetPosition)) {
            return attack(sourcePiece, chessmen.findByPosition(targetPosition));
        }
        sourcePiece.move(targetPosition, chessmen::isOccupied);
        return continueGame();
    }

    private Game attack(Piece sourcePiece, Piece targetPiece) {
        sourcePiece.kill(targetPiece, chessmen::isOccupied);
        chessmen.delete(targetPiece);

        if (chessmen.countKings() < ONGOING_GAME_KING_COUNT) {
            return new GameOver(chessmen);
        }
        return continueGame();
    }

    abstract protected void validateTurn(Piece sourcePiece);

    abstract protected Game continueGame();

    private Position sourcePositionOf(MoveCommandDto dto) {
        String source = dto.source();
        return Position.of(source);
    }

    private Position targetPositionOf(MoveCommandDto dto) {
        String target = dto.target();
        return Position.of(target);
    }

    @Override
    public final boolean isEnd() {
        return false;
    }
}
