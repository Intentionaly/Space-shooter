package common.data;

public class GameData {

        private int displayWidth  = 800;
        private int displayHeight = 825;
        private final GameKeys keys = new GameKeys();


        public GameKeys getKeys() {
            return keys;
        }

        public int getDisplayWidth() {
            return displayWidth;
        }

        public int getDisplayHeight() {
            return displayHeight;
        }


    }
