import java.util.Collections;
import java.util.List;

public class PrepareDataset {

    public static EntityResult trainTestSplit(List<Entity> flowerList) {
        Collections.shuffle(flowerList);
        int trainSize = (int)(flowerList.size() * 0.7);
        List<Entity> trainSet = flowerList.subList(0, trainSize);
        List<Entity> testSet = flowerList.subList(trainSize, flowerList.size());
        return new EntityResult(trainSet, testSet);
    }
}
