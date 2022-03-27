package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MovePositionCommandDto;

public class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;

    public Running(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    public Game moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = chessmen.findByPosition(sourcePositionOf(dto));
        Position targetPosition = targetPositionOf(dto);

        if (chessmen.isOccupied(targetPosition)) {
            return attack(sourcePiece, chessmen.findByPosition(targetPosition));
        }
        sourcePiece.move(targetPosition, chessmen::isOccupied);
        return new Running(chessmen);
    }

    private Position sourcePositionOf(MovePositionCommandDto dto) {
        String source = dto.source();
        return Position.of(source);
    }

    private Position targetPositionOf(MovePositionCommandDto dto) {
        String target = dto.target();
        return Position.of(target);
    }

    private Game attack(Piece sourcePiece, Piece targetPiece) {
        sourcePiece.kill(targetPiece, chessmen::isOccupied);
        chessmen.delete(targetPiece);

        if (chessmen.countKings() < ONGOING_GAME_KING_COUNT) {
            return new GameOver(chessmen);
        }
        return new Running(chessmen);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String toString() {
        return "Running{" + "chessmen=" + chessmen + '}';
    }
}
