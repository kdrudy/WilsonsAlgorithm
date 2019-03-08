package com.kyrutech.wilsons;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WilsonsAlgorithm extends ApplicationAdapter {

    SpriteBatch batch;
    int width = 100;
    int height = 100;
    Maze maze = new Maze(width, height);

    Pixmap pixmap;
    int cellSize = 600 / width / 2;
    int cellPadding = cellSize / 2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        pixmap = new Pixmap(600, 600, Format.RGBA8888);
    }

    @Override
    public void render() {
        if (!maze.isComplete()) {
            maze.next();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Texture tex = new Texture(generatePixmap());
        batch.draw(tex, 0, 0);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private Pixmap generatePixmap() {
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        pixmap.setColor(Color.WHITE);
        for (Cell cell : maze.maze) {
            drawCell(cell);
        }
        pixmap.setColor(Color.BLUE);
        for(Cell cell : maze.path) {
            drawCell(cell);
        }

        return pixmap;
    }

    private void drawCell(Cell cell) {
        int totalCellSize = cellPadding + cellSize + cellPadding;

        int x = (cell.x * totalCellSize) + cellPadding;
        int y = (cell.y * totalCellSize) + cellPadding;
        pixmap.fillRectangle(x, y, cellSize, cellSize);
        if (cell.north) {
            pixmap.fillRectangle(x, y - cellPadding, cellSize, cellPadding);
        }
        if (cell.south) {
            pixmap.fillRectangle(x, y + cellSize, cellSize, cellPadding);
        }
        if (cell.east) {
            pixmap.fillRectangle(x + cellSize, y, cellPadding, cellSize);
        }
        if (cell.west) {
            pixmap.fillRectangle(x - cellPadding, y, cellPadding, cellSize);
        }
    }
}
