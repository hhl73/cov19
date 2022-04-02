package sample;

/**
 * 空间坐标类：
 * 包括左上角坐标x，y，空间宽度width，长度height
 */
public class Space {
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * 构造一个空间坐标：
     * @param x 空间坐标左上角x。
     * @param y 空间坐标左上角y。
     * @param width 空间宽度。
     * @param height 空间长度。
     */
    Space(double x ,double y,double width,double height ){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     *
     * @return 空间左上角x坐标。
     */
    public double getX(){
        return x;
    }

    /**
     *
     * @return 空间左上角y坐标。
     */
    public double getY(){
        return y;
    }

    /**
     *
     * @return 空间宽度。
     */
    public double getWidth(){
        return width;
    }

    /**
     *
     * @return 空间长度。
     */
    public double getHeight(){
        return height;
    }
}