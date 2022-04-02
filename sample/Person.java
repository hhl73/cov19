package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

/**
 * 人类个体类
 */
public class Person extends Pane {
    /**
     * 用一个圆来表示个体的图形。
     * 蓝色代表没有感染，黄色代表初步感染，橙色对标开始传染他人，红色代表以及出现明显症状（自动去意愿）。
     */
    private Circle circle ;
    /**
     *个体身上的病毒。
     */
    private Virus virus ;

    /**
     * 个体的活动空间。
     */
    private Space space ;

    /**
     * 个体移动的目标空间。
     */
    private Space target;

    /**
     * 所居住的房子的编号。
     */
    private int houseNum;
    /**
     * 从得病开始的更新次数，模拟时间的推移。
     */
    private int cotGetSick = 0;

    /**
     * 在同一个空间滞留的时间（用更新次数模拟）。
     */
    private  int cotLive = 0;

    /**
     * 购物意愿，0表示无购物意愿，1表示有。
     * 若被要求居家隔离，则购物意愿为0.
     */
    private int  likeShopping;

    /**
     * 构造参数。
     */
    Person(){
        virus = new Virus();
        likeShopping = 0;
        circle = new Circle(3);
        space = new Space(100,100,300,200);
        houseNum = (int)(Math.random()*10);
        target = BackGround.getHouse(houseNum);
        circle.setCenterX(space.getX()+Math.random()*space.getWidth());
        circle.setCenterY(space.getY()+Math.random()*space.getHeight());
        circle.setFill(Color.BLUE);
        getChildren().add(circle);
    }

    /**
     *
     * @return 当前坐标的x。
     */
    public double getX(){
        return circle.getCenterX();
    }

    /**
     *
     * @return 当前坐标的y。
     */
    public double getY(){
        return circle.getCenterY();
    }

    /**
     *
     * @return 返回个体带有的病毒等级。
     */
    public int getIsSick(){
        return this.virus.getLevel();
    }

    /**
     *
     * @return 方晖房子标号。
     */
    public int getHouseNum(){
        return houseNum;
    }

    /**
     *
     * @return 放回活动空间。
     */
    public Space getSpace(){ return space; }

    /**
     *
     * @param newSpace 新设置的活动空间。
     */
    public void setSpace(Space newSpace){
        space = newSpace;
    }

    /**
     *
     * @return 返回购物意愿。
     */
    public int getLikeShopping(){
        return likeShopping;
    }

    /**
     *
     * @param x 新设置的购物意愿。
     */
    public void setLikeShopping(int x){ likeShopping = x;}

    /**
     *更新个体当前状态：
     *     如果病毒等级到达3级，则把颜色更新成红色，设置医院空间为目标空间。
     *     如果在同一个空间滞留太长时间则更换目标空间（医院空间除外）。
     *     更新病毒等级。
     *     遍历所有个体，接近且自身病毒等级达到2级以上的个体有可能传染病毒。
     * @param people 所有空间内人群的引用。
     */
    public void update(ArrayList<Person> people){
        cotLive++;
        if(this.virus.getLevel()>=3){
            target = BackGround.hospital;
            return;
        }

        if(cotLive==100){
            cotLive = 0;
            if(likeShopping==1&&target==BackGround.getHouse(houseNum)){
                target = BackGround.superMarket;
            }
            else if(target==BackGround.superMarket){
                target = BackGround.getHouse(houseNum);
                cotLive = 0;
            }
        }

        if(virus.getLevel()==1||virus.getLevel()==2){
            if(virus.getLevel()==1){
                circle.setFill(Color.YELLOW);
            }else if(virus.getLevel()==2){
                circle.setFill(Color.ORANGE);
            }
            cotGetSick++;
            virus.upDateLevel(cotGetSick);
        }

        if(virus.getLevel()==3) {
            circle.setFill(Color.RED);
            target = BackGround.hospital;
        }

        for(Person p:people){
            if(p.virus.getLevel()>=2){
                double distance = Math.sqrt(Math.pow(p.getX()-this.getX(),2.0)+
                        Math.pow(p.getY()-this.getY(),2.0));
                if(distance<6&&Math.random()<Virus.rat){
                    if(virus.getLevel()==0){
                        circle.setFill(Color.YELLOW);
                        virus.setSick();
                    }
                }
            }
        }
    }

    /**
     * 个体感染。
     * 设置病毒等级。
     */
    public void getSick(){
        virus.setSick(); ;
        circle.setFill(Color.YELLOW);
    }

    /**
     * 向目标空间移动
     * 在目标空间内随机设置一个目标点。
     * 在离目标点足够接近之前，向目标点移动一小步。
     */
    public void goToTarget(){
        double endX = target.getX() + Math.random()*target.getWidth();
        double endY = target.getY() + Math.random()*target.getHeight();
        //if(space != target) {
        if (circle.getCenterX() - endX > 5) {
            circle.setCenterX(circle.getCenterX() - 4);
        }
        if (endX - circle.getCenterX() > 5) {
            circle.setCenterX(circle.getCenterX() + 4);
        }
        if (endY - circle.getCenterY() > 5) {
            circle.setCenterY(circle.getCenterY() + 4);
        }
        if (circle.getCenterY() - endY > 5) {
            circle.setCenterY(circle.getCenterY() - 4);
        }

        //}
    }

}