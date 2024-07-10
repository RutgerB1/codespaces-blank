package com.main;

import object.OBJ_Keycard;

public class AssetSetter {

    GamePanel gp;


    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Keycard(gp);
        gp.obj[0].worldX = 50 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;

    }
}