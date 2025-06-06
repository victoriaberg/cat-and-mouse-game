package se.liu.vicbe988.background;

import se.liu.vicbe988.entity.Entity;

public class CollisionControll {

    GamePanel gamePanel;

    public CollisionControll(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
	// Checking left, right, top and bottom of solid area in player
	int entityLeftWorldX = entity.getMapX() + entity.getSolidArea().x;
	int entityRightWorldX = entity.getMapX() + entity.getSolidArea().x + entity.getSolidArea().width;
	int entityTopWorldY = entity.getMapY() + entity.getSolidArea().y;
	int entityBottomWorldY = entity.getMapY() + entity.getSolidArea().y + entity.getSolidArea().height;

	int entityLeftCol = entityLeftWorldX/gamePanel.TILE_SIZE;
	int entityRightCol = entityRightWorldX/gamePanel.TILE_SIZE;
	int entityTopRow = entityTopWorldY/gamePanel.TILE_SIZE;
	int entityBottomRow = entityBottomWorldY/gamePanel.TILE_SIZE;

	int tileNum1, tileNum2;

	switch (entity.direction) {
	    case UP:
		entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case DOWN:
		entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case LEFT:
		entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	    case RIGHT:
		entityRightCol = (entityRightWorldX + entity.getSpeed())/gamePanel.TILE_SIZE;
		tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
		tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
		if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
		    entity.collisionOn = true;
		}
		break;
	}
    }
}
