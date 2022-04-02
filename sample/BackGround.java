package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.format.TextStyle;
import java.util.ArrayList;

/**
 * 背景类：
 * 是BorderPane的子类，使用BorderPane进行布局。
 * 包含医院，商场，住宅区的空间坐标信息，和图形信息。
 */
public class BackGround extends BorderPane {
    /**
     * 医院的空间坐标。
     */
    public static final Space hospital = new Space(400,100,100,200);
    /**
     * 商场的空间坐标。
     */
    public static final Space superMarket = new Space(0,100,100,200);

    /**
     * 住宅区的空间坐标（各个房子空间坐标的集合）。
     */
    private static ArrayList<Space> house = new ArrayList<>();

    /**
     * 上排房子的图形信息。
     */
    Pane top = new houseSpace();
    /**
     * 下排房子的图形信息。
     */
    Pane bottom = new houseSpace();
    /**
     * 商场的图形信息。
     */
    Pane left = new bigSpace("商场");
    /**
     * 医院的图形信息。
     */
    Pane right = new bigSpace("医院");

    /**
     * 构造函数，将各个图形放到窗格上，同时分配各个房子的坐标信息。
     */
    BackGround(){
        //HBox houseSpace = new HBox();
        super.setBottom(bottom);
        super.setTop(top);
        super.setLeft(left);
        super.setRight(right);

        for(int i =0;i<5;i++){
            house.add(new Space(20+i*100,20,60,60));
        }
        for(int i=0;i<5;i++){
            house.add(new Space(20+i*100,320,60,60));
        }
    }

    /**
     * 查找房子的坐标信息
     * @param i 房子的编号。
     * @return  房子的坐标信息。
     */
    public static Space getHouse(int i){
        return house.get(i);
    }
}

/**
 * 住宅区的图形类：
 * 是StackPane的子类，将房子的图形和“住宅区”标签堆叠上去。
 */
class houseSpace extends StackPane {
    /**
     * 构造一个住宅区空间。
     */
    houseSpace(){
        HBox picture = new HBox();
        picture.setAlignment(Pos.CENTER);
        picture.setPadding(new Insets(20,20,20,20));
        Rectangle[] houses = {new Rectangle(60,60, Color.LIGHTBLUE),
                new Rectangle(40,60,Color.WHITE),
                new Rectangle(60,60,Color.LIGHTBLUE),
                new Rectangle(40,60,Color.WHITE),
                new Rectangle(60,60,Color.LIGHTBLUE),
                new Rectangle(40,60,Color.WHITE),
                new Rectangle(60,60,Color.LIGHTBLUE),
                new Rectangle(40,60,Color.WHITE),
                new Rectangle(60,60,Color.LIGHTBLUE),};
        picture.getChildren().addAll(houses);
        super.getChildren().add(picture);
        Text name = new Text("住宅区");
        name.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        super.getChildren().add(name);
    }
}

/**
 * 大型聚集区类：
 * 可以用来实例化出商场/医院的图形界面。
 */
class bigSpace extends StackPane {
    /**
     *
     * @param name1 大型聚集区的标签，例如：商场/医院。
     */
    bigSpace(String name1){
        Rectangle market = new Rectangle(100,200,Color.LIGHTBLUE);
        super.getChildren().add(market);
        Text name = new Text(name1);
        name.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        super.getChildren().add(name);
    }
}
