/**
 * {@code @Description}  验证码
 *
 * @author liyajun
 * {@code @create}          2022-11-16 22:16
 */

package com.group7.sanSongMall.util;

import io.swagger.annotations.ApiModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@ApiModel("验证码生成器")
public class CreateVerifyCodeImage {

    /**
     * 宽度
     */
    private static final int WIDTH = 90;
    /**
     * 高度
     */
    private static final int HEIGHT = 35;
    /**
     * 字体大小
     */
    private static final int FONT_SIZE = 20;
    /**
     * 验证代码
     */
    private static char [] verifyCode;
    /**
     * 验证码图片
     */
    private static BufferedImage verifyCodeImage;

    /**
     * 获取验证码图片
     *
     * @return {@link BufferedImage}
     */
    public static BufferedImage getVerifyCodeImage(){
        verifyCodeImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
        Graphics graphics = verifyCodeImage.getGraphics();
        verifyCode = generateCheckCode();
        drawBackground(graphics);
        generateVerifyCodeImage(graphics,verifyCode);
        graphics.dispose();
        return verifyCodeImage;
    }

    /**
     * 获取验证码
     *
     * @return {@link char} {@link []}
     */
    public static char [] getVerifyCode(){
        return verifyCode;
    }


    /**
     * 生成校验码
     *
     * @return {@link char} {@link []}
     */
    public static char [] generateCheckCode(){
        String chars = "0123456789abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char [] rands = new char[4];
        for(int i = 0;i < 4;i++){
            int randNum = (int) (Math.random() * (10 + 26 * 2)) % 61;
            rands[i] = chars.charAt(randNum);
        }
        return rands;
    }

    /**
     * 生成验证码图片
     *
     * @param graphics   图形
     * @param verifyCode 验证代码
     */
    public static void generateVerifyCodeImage(Graphics graphics,char [] verifyCode){
        graphics.setFont(new Font("Console", Font.BOLD, FONT_SIZE));
        for(int i = 0;i < 4;i++) {
            graphics.setColor(getRandomColor());
            graphics.drawString("" + verifyCode[i],i * FONT_SIZE+10,25 );
        }
    }

    /**
     * 画背景
     *
     * @param graphics 验证码图片
     */
    private static void drawBackground(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
    }
    /**
     * 得到随机颜色
     *
     * @return {@link Color}
     */
    public static Color getRandomColor(){
        Random rad = new Random();
        return new Color(rad.nextInt(220),rad.nextInt(220),rad.nextInt(220));
    }

}