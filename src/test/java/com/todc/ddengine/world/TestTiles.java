package com.todc.ddengine.world;


import com.todc.ddengine.data.Tiles;
import org.junit.Test;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author Tim O'Donnell (tim@timodonnell.com)
 */
public class TestTiles {

    @Test
    public void testLoad() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("tiles.yaml");
        try {
            Tiles.load(is);
            Tile tile = Tiles.getTileByGlyph(".");
            assertEquals("Floor", tile.getName());
            assertEquals(".", tile.getGlyph().getCharacter());
            assertEquals(Color.WHITE, tile.getGlyph().getForeground());
            assertEquals(Color.BLACK, tile.getGlyph().getBackground());
        } catch (IOException ex) {
            fail("Unexpected IOException: " + ex.getMessage());
        }
    }

    @Test
    public void testGetTileByName() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("tiles.yaml");
        try {
            Tiles.load(is);
            Tile tile = Tiles.getTileByName(Tiles.FLOOR_NAME);
            assertEquals("Floor", tile.getName());
            assertEquals(".", tile.getGlyph().getCharacter());
            assertEquals(Color.WHITE, tile.getGlyph().getForeground());
            assertEquals(Color.BLACK, tile.getGlyph().getBackground());
        } catch (IOException ex) {
            fail("Unexpected IOException: " + ex.getMessage());
        }
    }

}
