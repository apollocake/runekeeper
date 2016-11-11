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

    public void drawBox(Rectangle bounds) {

        //Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        super.begin(ShapeType.Line);
        super.setColor(Color.RED);
        //don't use bounds width and height, resizing transormation pattern makes it moot
        super.rect(bounds.x, bounds.y, bounds.width, bounds.height); 
        //Gdx.gl.glDisable(GL20.GL_BLEND);
        super.end();
    }
        public void drawBox(Rectangle bounds, Vector2 newDimensions) {

        //Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        super.begin(ShapeType.Line);
        super.setColor(Color.RED);
        //don't use bounds width and height, resizing transormation pattern makes it moot
        super.rect(bounds.x, bounds.y, newDimensions.x, newDimensions.y); 
        //Gdx.gl.glDisable(GL20.GL_BLEND);
        super.end();
    }
}
