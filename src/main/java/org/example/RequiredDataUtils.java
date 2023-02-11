package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequiredDataUtils {
    //at least pressed 'numberNeedBePressed' keys to pass the verification
    private static int numberNeedBePressed = 4;

    //初始化键盘
    private static Map<String,String> legalKeys = initMap();

    //至少需要按多少秒
    private static double requiredPressTime = calculatePressTime();

    //初始化键盘位置，用来计算中心点
    private static Map<String,Keys> keysLocationMap = setKeysLocation();

    //初始化键盘的位置
    private static Map<String,Keys> setKeysLocation(){
        Map<String,Keys> map = new HashMap<String, Keys>();

        String temp = "1 2 3 4 5 6 7 8 9 0 - =";
        String[] fifthLine = temp.split(" ");
        for (int i = 0; i < fifthLine.length; i ++){
            map.put(fifthLine[i], new Keys(fifthLine[i], 5, i + 1 ));
        }

        temp = "Q W E R T Y U I O P [ ] \\";
        String[] fourthLine = temp.split(" ");
        for (int i = 0; i < fourthLine.length; i++){
            map.put(fourthLine[i], new Keys(fourthLine[i],4, i + 1.5 ));
        }

        temp = "A S D F G H J K L ; '";
        String[] line = temp.split(" ");
        for (int i = 0; i < line.length; i++){
            map.put(line[i], new Keys(line[i],3, i + 1.75 ));
        }

        temp = "Z X C V B N M , . /";
        line = temp.split(" ");
        for (int i = 0; i < line.length; i++){
            map.put(line[i], new Keys(line[i],3, i + 2.25 ));
        }

        return map;
    }

    //计算时长算法
    private static int calculatePressTime() {
        return 2;
    }

    // 计算密码的中心位置
    public static Keys calculateCentralPoint(Set<String> keyPressed){
        int row = 0;
        int column = 0;

        for (String key: keyPressed){
            row += keysLocationMap.get(key).getRow();
            column += keysLocationMap.get(key).getColumn();
        }

        row = row /keyPressed.size();
        column = column /keyPressed.size();

        for (String str: keysLocationMap.keySet()){
            if (row == (int)keysLocationMap.get(str).getRow() && column == (int)keysLocationMap.get(str).getColumn()){
                return keysLocationMap.get(str);
            }
        }

        return null;
    }


    public static int getNumberNeedBePressed() {
        return numberNeedBePressed;
    }

    public static void setNumberNeedBePressed(int numberNeedBePressed) {
        RequiredDataUtils.numberNeedBePressed = numberNeedBePressed;
    }

    public static double getRequiredPressTime() {
        return requiredPressTime;
    }

    public static void setRequiredPressTime(double requiredPressTime) {
        RequiredDataUtils.requiredPressTime = requiredPressTime;
    }

    public static Map<String, Keys> getKeysLocation() {
        return keysLocationMap;
    }

    public static void setKeysLocation(Map<String, Keys> keysLocation) {
        RequiredDataUtils.keysLocationMap = keysLocation;
    }

    public static Map<String, String> getLegalKeys() {
        return legalKeys;
    }

    public static void setLegalKeys(Map<String, String> legalKeys) {
        RequiredDataUtils.legalKeys = legalKeys;
    }

    public static Map<String, String> initMap() {
        Map<String,String> initMap = new HashMap<String, String>();
        initMap.put("空格","Blank");
        initMap.put("减号","-");
        initMap.put("等号","=");
        initMap.put("Caps Lock","Capslock");
        initMap.put("右方括号","]");
        initMap.put("左方括号","[");
        initMap.put("逗号",",");
        initMap.put("句点",".");
        initMap.put("斜杠","/");
        initMap.put("分号",";");
        initMap.put("引号","'");
        initMap.put("反斜杠","\\");
        initMap.put("后引号","`");
        initMap.put("Enter","\n");
        return initMap;
    }
}
