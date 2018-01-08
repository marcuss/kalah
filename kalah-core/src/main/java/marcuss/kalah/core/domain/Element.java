package marcuss.kalah.core.domain;

import java.io.Serializable;

public abstract class Element implements Serializable{
   public abstract Integer getSeeds();
   public abstract void setSeeds(Integer seeds);
   public abstract Move.Turn getOwner();
}
