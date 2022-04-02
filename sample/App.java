package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;

import static javafx.animation.Animation.INDEFINITE;


public class App extends Application {
    /**
     * 空间内生活的个体人数。
     */
    private static final int personNum = 200;

    /**
     *
     * @param stage 疫情情况模拟的舞台，另有一个舞台放置控制按钮，按下“开放购物”按钮后，人群中每隔一段时间会出现一部分人去商场购物；
     *              按下“居家隔离”按钮后，人群会自动隔离在自己的房子内；
     *              变为红色的个体表示已经表现出明显病症，会自动移动到医院区域。
     *
     */
    @Override
    public void start(Stage stage) throws Exception {

        /**
         * 设置背景
         */
        StackPane pane = new StackPane();
        BackGround backGround = new BackGround();
        pane.getChildren().add(backGround);

        /**
         * 初始化人类对象
         */
        ArrayList<Person> people = new ArrayList<>();
        for(int i=0;i<personNum;i++){
            people.add(new Person());
            pane.getChildren().add(people.get(i));
        }
        people.get((int)Math.random()*personNum).getSick();

        /**
         *设置按钮显示
         */
        Button shoppingButton = new Button("开放购物");
        Button stayHomeButton = new Button("居家隔离");
        HBox hBox = new HBox(shoppingButton,stayHomeButton);
        hBox.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(hBox);
        Stage anotherStage = new Stage();
        anotherStage.setScene(newScene);
        anotherStage.show();


        /**
         *时钟设置
         */
        EventHandler<ActionEvent> change = e->{
            for(int i=0;i<personNum;i++){
                people.get(i).goToTarget();
                people.get(i).update(people);
            }
        };
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(100),change));
        clock.setCycleCount(INDEFINITE);
        clock.play();


        /**
         * 居家隔离按钮
         */
        stayHomeButton.setOnMouseClicked(e->{
            for(Person person : people){
                person.setLikeShopping(0);
            }
        });

        /**
         * 开放购物按钮设置。
         */
        shoppingButton.setOnMouseClicked(e->{
            for(Person person : people){
                person.setLikeShopping((int)(Math.random()*2));
            }
        });

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
