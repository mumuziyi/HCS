package org.example;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyPressed implements NativeKeyListener {

    //
    public static Set<String> password = new HashSet<String>();

    // store pressed and unreleased keys
    public static Set<String> pressedKeysSet = new HashSet<String>();

    public static boolean startVerification = false;

    public static boolean newTime = true;

    //calculate time
    private static Date date;

    File file = new File("./public/test.txt");
    FileWriter fw;


    public void nativeKeyPressed(NativeKeyEvent e) {
        // get input
        String pressed = NativeKeyEvent.getKeyText(e.getKeyCode());

        Set<String> legalSet = RequiredDataUtils.getKeysLocation().keySet();

        // map keys to legal characters, better for process
        if (RequiredDataUtils.getLegalKeys().containsKey(pressed)){
            pressed = RequiredDataUtils.getLegalKeys().get(pressed);
        }

        // 如果不是合法字符,忽略这次输入
        if (!legalSet.contains(pressed) && pressed != "\n"){
            return;
        }

        // start verification after typing the 'Enter'
        if (pressed == "\n"){
            //
            if (password.size() == 0){
                System.out.println("please enter the password before enter the password");
            }else {
                System.out.println("Start verification");
                Keys keys = RequiredDataUtils.calculateCentralPoint(password);
                assert keys != null;
                String str ="You need to hold " + RequiredDataUtils.getNumberNeedBePressed() +" keys around the "
                        + keys.getKey() + " for " + RequiredDataUtils.getRequiredPressTime() + " seconds";
                try {
                    fw = new FileWriter(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fw.append(str);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(str + " was added to the test file ");
                try {
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                startVerification = true;
                return;
            }

        }

        // 如果当前按键已经被按下，则什么也不做，处理长按算多次按下这一情况
        // 如果没有被按下，进入处理
        if (!pressedKeysSet.contains(pressed)){

            //如果还没有开始验证，代表正在输入密码，保存密码到password中
            if (!startVerification){
                password.add(pressed);
            }

            System.out.println("press " + pressed);

            // add the pressed keys to the set;
            if (startVerification){
                pressedKeysSet.add(pressed);
//                System.out.println(pressedKeysSet.size());
                //如果按键数量达标，初始化data，记录当前时间。用newTime保证更多按键加入时不会重置倒计时
                if (pressedKeysSet.size() >= RequiredDataUtils.getNumberNeedBePressed() && newTime){
                    date = new Date();
                    System.out.println("meet Requirement, please hold");
                    System.out.println("start time is " + date.getTime());
                    newTime = false;
                }
            }

        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        // 用户松开的按键
        String released = NativeKeyEvent.getKeyText(e.getKeyCode());
        // 映射中文字符
        if (RequiredDataUtils.getLegalKeys().containsKey(released)){
            released = RequiredDataUtils.getLegalKeys().get(released);
        }

        if (released == "\n"){
            return;
        }

        if (startVerification){
            // 如果按键数量达到标准
            if (pressedKeysSet.size() >= RequiredDataUtils.getNumberNeedBePressed()){
                Date endDate = new Date();
                long correctPressedTime = endDate.getTime() - date.getTime();
                // 判断按压时长
                if (correctPressedTime/1000 > RequiredDataUtils.getRequiredPressTime()){
                    System.out.println("pass The verification");
                    startVerification = false;
                    password = new HashSet<String>();
                }
                else {
                    System.out.println("didn't meet the time requirement: " +
                            RequiredDataUtils.getRequiredPressTime() + "s");
                    System.out.println("Please retry");
                    startVerification = false;
                    password = new HashSet<String>();
                }
            }
            // 按键数量不够
            else {
                System.out.println("Number of pressed keys less than requirement: " +
                        RequiredDataUtils.getNumberNeedBePressed());
                System.out.println("please retry");
                startVerification = false;
                password = new HashSet<String>();
            }
        }

        // 重置系统
        newTime = true;
        pressedKeysSet.remove(released);
    }


    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new KeyPressed());
    }
}