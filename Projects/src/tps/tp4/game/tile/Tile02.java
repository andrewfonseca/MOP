package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Church;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile02 extends Tile {

    public Tile02() {
        super(
                new Field(),
                new Field(),
                new Road(),
                new Field(),
                new Church(),
                2,
                "Tile02_ffrf_i_x2.jpg"
        );
    }

    @Override
    protected Tile02 poly_clone() {
        return new Tile02();
    }
}
