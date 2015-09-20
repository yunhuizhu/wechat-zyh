package com.yuncia.wechat.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-7
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class CustomDecrypt {
    //加密字符
    private static final String[] EncryptionChar = new String[]{"A","b","C","d","E"
            ,"f","G","h","I","j"
            ,"K","l","M","n","O"
            ,"p","Q","r","S","t"
            ,"U","v","W","x","Y"
            ,"z","a","B","c","D"
            ,"e","F","g","H","i"
            ,"J","k","L","m","N"
            ,"o","P","q","R","s"
            ,"T","u","V","w","X"
            ,"y","Z"};
    //解密字符
    private static final String[] DecryptionChar= new String[]{"0","1","2","3","4","5","6","7","8","9"};

    private static final Map<String, String> encryptionMap = new HashMap<String, String>();
    private static final Map<String,List<String>> decryptionMap= new HashMap<String, List<String>>();
    static {
        for(int i=0;i<DecryptionChar.length;i++){
            List<String> list = new ArrayList<String>();
            for(int j=1;j<=5;j++){
                encryptionMap.put(EncryptionChar[(i+1)*5-j], DecryptionChar[i]);
                list.add(EncryptionChar[(i+1)*5-j]);
            }
            decryptionMap.put(DecryptionChar[i],list);
        }
    }
    //加密
    public static String encryption(String decryptionString){
        StringBuffer stringBuffer = new StringBuffer();
        //长度小于7位数，补足7位
        if(decryptionString != null && decryptionString.length()<=7){
            //对数字加密成字符
            for(int i=0;i<decryptionString.length();i++){
                List<String> list = decryptionMap.get(String.valueOf(decryptionString.charAt(i)));
                int num = (int)(getRandom()*5);
                stringBuffer.append(list.get(num));
            }
            //随机取得加密字符数组的后两位补足7位
            for(int i=0;i<7-decryptionString.length();i++){
                int num = (int)(getRandom()*2)+1;
                stringBuffer.append(EncryptionChar[EncryptionChar.length-num]);
            }
            //长度大于7位数加密
        }else if(decryptionString != null && decryptionString.length()>7){
            for(int i=0;i<decryptionString.length();i++){
                List<String> list = decryptionMap.get(String.valueOf(decryptionString.charAt(i)));
                int num = (int)(getRandom()*5);
                stringBuffer.append(list.get(num));
            }
        }
        return stringBuffer.toString();
    }

    private static double getRandom() {
        //如果需要随机密文则返回随机数值
        //return Math.random();
        //如果需要固定密文则返回固定数值
        return 0.5;
    }
    //解密
    public static String decryption(String encryptionString){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<encryptionString.length();i++){
            String key = encryptionMap.get(String.valueOf(encryptionString.charAt(i)));
            if(key != null && !"".equals(key)){
                stringBuffer.append(key);
            }
        }
        return stringBuffer.toString();
    }

    //算法测试
    public static void main(String[] args) throws Exception {
        System.out.println(encryption("2647"));
		/*

		String decryptionString = "12346";
		System.out.println("明文："+decryptionString);
		StringBuffer stringBuffer = new StringBuffer();
		//长度小于7位数，补足7位
		double random = 0.5Math.random();
		if(decryptionString != null && decryptionString.length()<=7){
			//对数字加密成字符
			for(int i=0;i<decryptionString.length();i++){
				List<String> list = decryptionMap.get(String.valueOf(decryptionString.charAt(i)));
				int num = (int)(random*5);
				stringBuffer.append(list.get(num));
			}
			//随机取得加密字符数组的后两位补足7位
			for(int i=0;i<7-decryptionString.length();i++){
				int num = (int)(random*2)+1;
				stringBuffer.append(EncryptionChar[EncryptionChar.length-num]);
			}
		//长度大于7位数加密
		}else if(decryptionString != null && decryptionString.length()>7){
			for(int i=0;i<decryptionString.length();i++){
				List<String> list = decryptionMap.get(String.valueOf(decryptionString.charAt(i)));
				int num = (int)(random*5);
				stringBuffer.append(list.get(num));
			}
		}
		String encryptionString = stringBuffer.toString();
		System.out.println("加密："+stringBuffer);


		//-----------
		stringBuffer = new StringBuffer();
		for(int i=0;i<encryptionString.length();i++){
			String key = encryptionMap.get(String.valueOf(encryptionString.charAt(i)));
			if(key != null && !"".equals(key)){
				stringBuffer.append(key);
			}
		}
		System.out.println("解密："+stringBuffer);

	*/}
}
