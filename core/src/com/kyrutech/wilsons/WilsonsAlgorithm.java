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
    int mazeWidth = 20;
    int mazeHeight = 20;
    Maze maze = new Maze(mazeWidth, mazeHeight);
    Texture tex;

    Pixmap pixmap;
    int cellSize = 2;
    int cellPadding = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        pixmap = new Pixmap(mazeWidth*cellSize*(cellPadding*2), mazeHeight*cellSize*(cellPadding*2), Format.RGBA8888);
    }

    @Override
    public void render() {
        if (!maze.isComplete()) {
            maze.next();
            tex = new Texture(generatePixmap());
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
