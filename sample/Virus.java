package sample;

import java.awt.*;

/**
 * 病毒类
 */
public class Virus {
    /**
     * 病毒等级
     * 0表示没有感染，1表示潜伏期期，2表示已经开始传染他人，3表示已经有明显症状。
     */
    private int level=0;


    /**
     * 病毒的感染率
     */
    public static final double rat = 0.2;

    /**
     * 等级1到等级2的潜伏期，单位是从感染病毒开始的更新次数。
     */
    private static final int incubationPeriod1 = 80;

    /**
     * 等级1到等级3的潜伏期，单位是从感染病毒开始的更新次数。
     */
    private static final int incubationPeriod2 = 200;

    /**
     * 受感染，使level由0置1.
     */
    public void setSick(){
        level = 1;
    }

    /**
     *
     * @return 返回病毒的等级。
     */
    public int getLevel(){
        return level;
    }

    /**
     * 更新病毒等级。
     * @param cot 患者从感染病毒开始的迭代次数，用于更新病毒等级。
     */
    public void upDateLevel(int cot){
        if(cot>incubationPeriod1&&level==1){
            level++;
        }
        else if(cot>incubationPeriod2&&level==2){
            level++;
        }
    }
}
