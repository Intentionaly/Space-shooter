package common.mothership;

import common.data.Entity;

public class Mothership extends Entity {

    private int MothershipHealth = 3;

    public void mothershipGotHit(){
        this.MothershipHealth--;
    }

    public int getMothershipHealth(){
        return MothershipHealth;
    }


}
