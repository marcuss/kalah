package marcuss.kalah.core.domain;

public abstract class Element {
   public abstract Integer getSeeds();
   public abstract void setSeeds(Integer seeds);
   public abstract Move.Turn getOwner();
}
