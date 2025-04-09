public class Entity {
    private String text;
    private int label;

    public Entity(String text, int label) {
        this.label = label;
        this.text = text;
    }

    @Override
    public String toString() {
        return text + " " + label;
    }

    public String getText(){
        return text;
    }

    public int getLabel(){
        return label;
    }
}

