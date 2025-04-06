package se.liu.vicbe988.background;

import se.liu.vicbe988.entity.Entity;

public class CollisionControll {

    GamePanel gamePanel;

    public CollisionControll(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
	// Checking left, right, top and bottom of solid area in player
	int entityLeftWorldX = entity.mapX + entity.solidArea.x;
	int entityRightWorldX = entity.mapX + entity.solidArea.x + entity.solidArea.width;
	int entityTopWorldY = entity.mapY + entity.solidArea.y;
	int entityBottomWorldY = entity.mapY + entity.solidArea.y + entity.solidArea.height;

	int entityLeftCol = entityLeftWorldX/gamePanel.TILE_SIZE;
	int entityRightCol = entityRightWorldX/gamePanel.TILE_SIZE;
	int entityTopRow = entityTopWorldY/gamePanel.TILE_SIZE;
	int entityBottomRow = entityBottomWorldY/gamePanel.TILE_SIZE;

	int tileNum1, tileNum2;

	switch (entity.direction) {
	    case "up":
		entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
		System.out.println("Up: Checking tiles [" + entityLeftCol + "][" + entityTopRow + "]=" + tileNum1 +
				   ", [" + entityRightCol + "][" + entityTopRow + "]=" + tileNum2);
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case "down":
		entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case "left":
		entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case "right":
		entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	}
    }
}
