package chess.service;

import chess.domain.chessGame.Turn;
import chess.domain.chessGame.generator.SpaceGenerator;
import chess.repository.ChessBoardRepository;

public class ChessBoardService {

    private final ChessBoardRepository chessBoardRepository;

    public ChessBoardService(ChessBoardRepository chessBoardRepository) {
        this.chessBoardRepository = chessBoardRepository;
    }

    public SpaceGenerator findSpaceGenerator() {
        return chessBoardRepository.findSpaceGenerator();
    }

    public Turn findTurn() {
        return chessBoardRepository.findTurn();
    }
}
