package chess.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.controller.dto.PieceDTO;
import chess.controller.dto.RoundStatusDTO;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.converter.StringPositionConverter;
import chess.domain.piece.Piece;
import chess.repository.ChessRepository;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final StringPositionConverter stringPositionConverter;
    private final ChessRepository chessRepository;

    public ChessController(ChessRepository chessRepository) {
        this.stringPositionConverter = new StringPositionConverter();
        this.chessRepository = chessRepository;
    }

    public List<PieceDTO> startGame(Long id) {
        ChessGame chessGame = chessRepository.createGame(id);
        List<Piece> pieces = chessGame.pieces().asList();
        return pieces.stream()
            .map(PieceDTO::new)
            .collect(toList());
    }

    public RoundStatusDTO roundStatus(Long gameId) {
        ChessGame chessGame = chessRepository.createGame(gameId);
        List<Piece> pieces = chessGame.currentColorPieces();
        return new RoundStatusDTO(
            mapMovablePositions(pieces),
            chessGame.currentColor(),
            chessGame.gameResult(),
            chessGame.isChecked(),
            chessGame.isKingDead()
        );
    }

    private Map<String, List<String>> mapMovablePositions(List<Piece> pieces) {
        return pieces.stream()
            .collect(toMap(
                piece -> piece.currentPosition().columnAndRow(),
                piece -> piece.movablePositions()
                    .stream()
                    .map(Position::columnAndRow)
                    .collect(toList())
            ));
    }

    public void move(Long gameId, String currentPosition, String targetPosition) {
        ChessGame chessGame = chessRepository.createGame(gameId);

        Position current = stringPositionConverter.convert(currentPosition);
        Position target = stringPositionConverter.convert(targetPosition);
        chessGame.movePiece(current, target);
    }

    public void restart(Long gameId) {
        chessRepository.restart(gameId);
    }

    public void exitGame(Long gameId) {
        chessRepository.endGame(gameId);
    }

    public void saveGame(Long gameId) {
        chessRepository.save(gameId);
    }
}
