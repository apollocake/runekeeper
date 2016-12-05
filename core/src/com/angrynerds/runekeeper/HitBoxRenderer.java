package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HitBoxRenderer extends ShapeRenderer {

    public HitBoxRenderer() {
    }

    public void drawBox(Rectangle bounds, Color color) {
        super.begin(ShapeType.Line);
        super.setColor(color);
        super.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        super.end();
    }

    public void drawBox(Rectangle bounds, Vector2 newDimensions, Color color) {
        super.begin(ShapeType.Line);
        super.setColor(color);
        //don't use bounds width and height on enemy, resizing transormation pattern makes it moot
        super.rect(bounds.x, bounds.y, newDimensions.x, newDimensions.y);
        super.end();
    }
}
