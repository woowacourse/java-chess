package chess.domain.game;

import chess.domain.piece.ActivePieces;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.MoveCommandDto;

abstract class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;

    Running(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    public final Game moveChessmen(MoveCommandDto dto) {
        Piece sourcePiece = chessmen.findByPosition(fromPosition(dto));
        Position targetPosition = toPosition(dto);
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

    private void validateTurn(Piece sourcePiece) {
        Color currentColor = currentTurnColor();
        if (!sourcePiece.hasColorOf(currentColor)) {
            throw new IllegalArgumentException(currentColor + " 진영이 움직일 차례입니다!");
        }
    }

    abstract protected Color currentTurnColor();

    abstract protected Game continueGame();

    private Position fromPosition(MoveCommandDto moveCommand) {
        String source = moveCommand.source();
        return Position.of(source);
    }

    private Position toPosition(MoveCommandDto moveCommand) {
        String target = moveCommand.target();
        return Position.of(target);
    }

    @Override
    public final boolean isEnd() {
        return false;
    }
}
