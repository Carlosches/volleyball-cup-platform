package userInterface;

import javafx.scene.shape.Rectangle;

public class ImageNode implements Comparable<ImageNode>{

    private ImageNode left;
    private ImageNode right;
    
    private String id;
    private Rectangle rectangle;
    
    public ImageNode(String id, Rectangle rectangle) {
        this.id = id;
        this.rectangle = rectangle;
    }

    /**
     * @return the left
     */
    public ImageNode getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public ImageNode getRight() {
        return right;
    }

    @Override
    public int compareTo(ImageNode other) {
        return id.compareTo(other.id);
    }

    /**
     * @param left the left to set
     */
    public void setLeft(ImageNode left) {
        this.left = left;
    }

    /**
     * @param right the right to set
     */
    public void setRight(ImageNode right) {
        this.right = right;
    }

    /**
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public String getId() {
        return id;
    }
}