package chess.repository;

import chess.domain.chessGame.Turn;
import chess.domain.chessGame.generator.SpaceGenerator;

public interface ChessBoardRepository {

    SpaceGenerator findSpaceGenerator();

    Turn findTurn();
}
