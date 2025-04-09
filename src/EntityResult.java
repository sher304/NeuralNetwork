import java.util.List;

public class EntityResult {
    public List<Entity> trainSet;
    public List<Entity> testSet;

    public EntityResult(List<Entity> trainSet, List<Entity> testSet) {
        this.trainSet = trainSet;
        this.testSet = testSet;
    }
}


