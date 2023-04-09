package study;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PawnMoveStrategy;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.ParameterizedTest;

public class PieceParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        if (extensionContext.getTestMethod()
                            .filter(method -> method.isAnnotationPresent(ParameterizedTest.class))
                            .isPresent()) {
            return parameterContext.getParameter()
                                   .getType() == Piece.class;
        }
        return false;
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        final String[] split = extensionContext.getRequiredTestInstance()
                                               .toString()
                                               .split(",");
        System.out.println(split[0]);
        return new Pawn(Color.WHITE, Position.of("b2"), PawnMoveStrategy.from(Color.WHITE));
    }
}
