package common.data;

public class GameData {
    /**
     *
     */

        private int displayWidth  = 800;
        private int displayHeight = 825;

        private  int kills;
        private final GameKeys keys = new GameKeys();

        private int MothershipHealth = 3;


        public GameKeys getKeys() {
            return keys;
        }

        public void mothershipGotHit(){
            this.MothershipHealth--;
        }

        public int getMothershipHealth(){
            return MothershipHealth;
        }

        public int getDisplayWidth() {
            return displayWidth;
        }

        public int getDisplayHeight() {
            return displayHeight;
        }

        public void setKills(int kills){
            this.kills = kills;
        }

        public int getKills(){
            return kills;
        }

        public void plusOneKill(){
            this.kills++;

        }
    }
